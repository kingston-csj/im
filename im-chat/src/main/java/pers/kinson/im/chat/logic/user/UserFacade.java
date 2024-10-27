package pers.kinson.im.chat.logic.user;

import io.netty.channel.Channel;
import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.listener.EventType;
import pers.kinson.im.chat.listener.annotation.EventHandler;
import pers.kinson.im.chat.logic.login.event.UserLoginEvent;
import pers.kinson.im.chat.logic.user.message.req.ReqUserRegister;

@Component
@MessageRoute
public class UserFacade {

    @Autowired
    private UserService userService;

    @EventHandler(value = {EventType.LOGIN})
    public void onUserLogin(UserLoginEvent loginEvent) {
        long userId = loginEvent.getUserId();
        User user = SpringContext.getUserService().getOnlineUser(userId);
        userService.refreshUserProfile(user);
    }

    @RequestHandler
    public void action(IdSession session, ReqUserRegister req) {
        UserService userService = SpringContext.getUserService();
        Channel channel = (Channel) session.getRawSession();
        userService.registerNewAccount(channel, req.getSex(), req.getUserId(), req.getPassword());
    }

}
