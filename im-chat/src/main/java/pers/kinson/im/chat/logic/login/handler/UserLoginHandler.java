package pers.kinson.im.chat.logic.login.handler;

import io.netty.channel.Channel;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.dispatch.MessageHandler;
import pers.kinson.im.chat.logic.login.LoginService;
import pers.kinson.im.chat.logic.login.message.req.ReqUserLogin;
import pers.kinson.im.chat.net.IoSession;

public class UserLoginHandler extends MessageHandler<ReqUserLogin> {

    @Override
    public void action(IoSession session, ReqUserLogin req) {
        Channel channel = session.getChannel();
        LoginService loginMgr = SpringContext.getBean(LoginService.class);
        loginMgr.validateLogin(channel, req.getUserId(), req.getUserPwd());
    }
}
