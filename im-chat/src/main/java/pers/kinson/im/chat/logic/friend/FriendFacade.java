package pers.kinson.im.chat.logic.friend;

import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.core.HttpResult;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.listener.EventType;
import pers.kinson.im.chat.listener.annotation.EventHandler;
import pers.kinson.im.chat.logic.friend.message.req.ReqApplyFriend;
import pers.kinson.im.chat.logic.login.event.UserLoginEvent;

@Component
@MessageRoute
public class FriendFacade {

    @Autowired
    private FriendService friendService;

    @EventHandler(value = {EventType.LOGIN})
    public void onUserLogin(UserLoginEvent loginEvent) {
        long userId = loginEvent.getUserId();
        User user = SpringContext.getUserService().getOnlineUser(userId);
        friendService.refreshUserFriends(user);
    }

    @RequestHandler
    public void action(IdSession session, ReqApplyFriend req) {
        int code = SpringContext.getBean(ApplyService.class).applyNewFriend(req.getFrom(), req.getTo(), req.getRemark());
        session.send(HttpResult.valueOf(code));
    }
}
