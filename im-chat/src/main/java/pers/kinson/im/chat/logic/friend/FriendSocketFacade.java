package pers.kinson.im.chat.logic.friend;

import jforgame.commons.NumberUtil;
import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.listener.EventType;
import pers.kinson.im.chat.listener.annotation.EventHandler;
import pers.kinson.im.chat.logic.friend.message.req.ReqQueryFriendsOnlineStatus;
import pers.kinson.im.chat.logic.friend.message.res.ResQueryFriendsOnlineStatus;
import pers.kinson.im.chat.logic.friend.message.vo.FriendItemVo;
import pers.kinson.im.chat.logic.friend.service.FriendService;
import pers.kinson.im.chat.logic.login.event.UserLoginEvent;

import java.util.LinkedList;
import java.util.List;

@Component
@MessageRoute
public class FriendSocketFacade {

    @Autowired
    private FriendService friendService;

    @EventHandler(value = {EventType.LOGIN})
    public void onUserLogin(UserLoginEvent loginEvent) {
        long userId = loginEvent.getUserId();
        User user = SpringContext.getUserService().getOnlineUser(userId);
        friendService.refreshUserFriends(user);
    }

    @RequestHandler
    public void reqQueryFriendsOnlineStatus(IdSession session, ReqQueryFriendsOnlineStatus req) {
        Long userId = NumberUtil.longValue(session.getId());
        List<Long> ids = new LinkedList<>();
        List<FriendItemVo> friendItemVos = friendService.listMyFriends(userId);
        for (FriendItemVo friendItemVo : friendItemVos) {
            if (SpringContext.getUserService().isOnlineUser(friendItemVo.getUserId())) {
                ids.add(friendItemVo.getUserId());
            }
        }
        ResQueryFriendsOnlineStatus response = new ResQueryFriendsOnlineStatus();
        response.setIds(ids);
        session.send(response);
    }

}
