package pers.kinson.im.chat.net;

import jforgame.socket.share.ChainedMessageDispatcher;
import jforgame.socket.share.CommonMessageHandlerRegister;
import jforgame.socket.share.IdSession;
import jforgame.socket.share.MessageHandler;
import jforgame.socket.share.MessageHandlerRegister;
import jforgame.socket.share.MessageParameterConverter;
import jforgame.socket.share.ThreadModel;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.message.MessageExecutor;
import jforgame.socket.share.message.MessageFactory;
import jforgame.socket.share.message.RequestDataFrame;
import jforgame.socket.share.task.MessageTask;
import jforgame.socket.support.DefaultMessageParameterConverter;
import pers.kinson.im.chat.base.SpringContext;

import java.io.IOException;
import java.util.Map;

public class MessageIoDispatcher extends ChainedMessageDispatcher {

    private MessageHandlerRegister handlerRegister;

    private MessageParameterConverter msgParameterConverter = new DefaultMessageParameterConverter(SpringContext.getBean(MessageFactory.class));

    private ThreadModel threadModel = SpringContext.getBean(ThreadModel.class);

    public MessageIoDispatcher() {
        MessageFactory messageFactory = SpringContext.getBean(MessageFactory.class);
        Map<String, Object> messageRoutes = SpringContext.getBeansWithAnnotation(MessageRoute.class);

        this.handlerRegister = new CommonMessageHandlerRegister(messageRoutes.values(), messageFactory);
        MessageHandler messageHandler = (session, frame) -> {
            RequestDataFrame dataFrame = (RequestDataFrame) frame;
            Object message = dataFrame.getMessage();
            int cmd = messageFactory.getMessageId(message.getClass());
            MessageExecutor cmdExecutor = handlerRegister.getMessageExecutor(cmd);
            if (cmdExecutor == null) {
                logger.error("message executor missed,  cmd={}", cmd);
                return true;
            }
            Object[] params = msgParameterConverter.convertToMethodParams(session, cmdExecutor.getParams(), dataFrame);
            Object controller = cmdExecutor.getHandler();

            // 匿名登录按session hash绑定，登录成功根据session自增长id绑定，更具负载均衡性
            long dispatchKey = session.hashCode();
//            if (session.getAttribute(SessionKeys.INDEX) != null) {
//                dispatchKey = NumberUtil.longValue(session.getAttribute(SessionKeys.INDEX));
//            }
            MessageTask task = MessageTask.valueOf(session, dispatchKey, controller, cmdExecutor.getMethod(), params);
            // 丢到任务消息队列，不在io线程进行业务处理
            threadModel.accept(task);
            return true;
        };

        addMessageHandler(messageHandler);
    }


    @Override
    public void onSessionCreated(IdSession session) {
    }

    @Override
    public void onSessionClosed(IdSession session) {
//        BaseGameTask task = new BaseGameTask() {
//            @Override
//            public void action() {
//                long userId = NumberUtil.longValue(session.getId());
//                SpringContext.getUserService().removeFromOnline(userId);
//            }
//        };
//        task.setDispatchKey(session.hashCode());
//        threadModel.accept(task);
    }

    public void exceptionCaught(IdSession session, Throwable cause) {
        if (!(cause instanceof IOException)) {
            this.logger.error("", cause);
        }
    }

}