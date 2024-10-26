package pers.kinson.im.chat.net.message;

import pers.kinson.im.chat.dispatch.MessageHandler;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.logs.LoggerUtils;
import pers.kinson.im.chat.net.IoSession;
import pers.kinson.im.chat.util.ClassScanner;
import pers.kinson.im.chat.core.HttpResult;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum MessageRouter {

    INSTANCE;

    private Map<Integer, MessageHandler> msgHandlers = new HashMap<>();

    private Map<Integer, Class<? extends AbstractPacket>> msgClazzs = new HashMap<>();

    MessageRouter() {
        Set<Class<?>> msgPool = ClassScanner.listAllSubclasses("pers.kinson.im.chat", AbstractPacket.class);
        msgPool.forEach(clazz -> {
            try {
                AbstractPacket packetObj = (AbstractPacket) clazz.newInstance();
                msgClazzs.put(packetObj.getPacketId(), (Class<? extends AbstractPacket>) clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        msgClazzs.put(CmdConst.ResCommon, HttpResult.class);

        Set<Class<?>> handlers = ClassScanner.listAllSubclasses("pers.kinson.im.chat", MessageHandler.class);
        handlers.forEach(c -> {
            try {
                Arrays.stream(c.getDeclaredMethods()).forEach(method -> {
                    // 第一个参数是  IoSession; 第二个参数是 AbstractPacket的非抽象子类
                    if (method.getParameterCount() == 2) {
                        if (method.getParameterTypes()[0] == IoSession.class) {
                            Class<?> secondParamType = method.getParameterTypes()[1];
                            if (AbstractPacket.class.isAssignableFrom(secondParamType)) {
                                if (!Modifier.isAbstract(secondParamType.getModifiers())) {
                                    try {
                                        MessageHandler handler = (MessageHandler) c.newInstance();
                                        AbstractPacket packetObj = (AbstractPacket) secondParamType.newInstance();
                                        // 绑定消息与handler
                                        MessageHandler oldHandler = msgHandlers.put(packetObj.getPacketId(), handler);
                                        if (oldHandler != null) {
                                            LoggerUtils.error("消息[[]]重复注册路由", secondParamType.getName());
                                            System.exit(1);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    return;
                                }
                            }
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void execPacket(IoSession session, AbstractPacket pact) {
        if (pact == null) return;
        try {
            msgHandlers.get(pact.getPacketId()).action(session, pact);
        } catch (Exception e) {
            LoggerUtils.error("", e);
        }
    }

    public AbstractPacket createNewPacket(int packetType) {
        Class<? extends AbstractPacket> packetClass = msgClazzs.get(packetType);
        if (packetClass == null) {
            throw new IllegalPacketException("类型为" + packetType + "的包定义不存在");
        }
        AbstractPacket packet = null;
        try {
            packet = packetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalPacketException("类型为" + packetType + "的包实例化失败");
        }

        return packet;
    }

}
