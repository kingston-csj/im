package pers.kinson.im.chat.logic.user.handler;

import io.netty.channel.Channel;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.dispatch.MessageHandler;
import pers.kinson.im.chat.logic.user.UserService;
import pers.kinson.im.chat.logic.user.message.req.ReqUserRegister;
import pers.kinson.im.chat.net.IoSession;

public class UserRegisterHandler extends MessageHandler<ReqUserRegister> {
    @Override
    public void action(IoSession session, ReqUserRegister req) {
        UserService userService = SpringContext.getUserService();
        Channel channel = session.getChannel();
        userService.registerNewAccount(channel, req.getSex(), req.getUserId(), req.getPassword());
    }
}
