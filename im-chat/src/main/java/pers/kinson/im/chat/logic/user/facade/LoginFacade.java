package pers.kinson.im.chat.logic.user.facade;

import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.stereotype.Controller;
import pers.kinson.business.entity.User;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.listener.EventType;
import pers.kinson.im.chat.logic.user.event.UserLoginEvent;
import pers.kinson.im.chat.logic.user.message.ReqConnectSocket;
import pers.kinson.im.chat.logic.user.message.ResConnectServer;

@Controller
@MessageRoute
public class LoginFacade {

    @RequestHandler
    public void action(IdSession session, ReqConnectSocket req) {
        User user = SpringContext.getUserService().queryUser(req.getUserId());
        SessionManager.INSTANCE.registerSession(user, session);
        session.send(new ResConnectServer());
        SpringContext.getEventDispatcher().fireEvent(new UserLoginEvent(EventType.LOGIN, req.getUserId()));

        SpringContext.getUserService().refreshUserProfile(user);
    }

}
