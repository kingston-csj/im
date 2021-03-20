package pers.kinson.im.chat.logic.login.handler;

import pers.kinson.im.chat.dispatch.MessageHandler;
import pers.kinson.im.chat.logic.login.message.req.ReqHeartBeat;
import pers.kinson.im.chat.net.IoSession;


public class HeartbeatHandler extends MessageHandler<ReqHeartBeat> {

    @Override
    public void action(IoSession session, ReqHeartBeat E) {
        System.err.println("收到客户端的心跳包");
    }
}
