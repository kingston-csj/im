package pers.kinson.im.chat.logic.redpoint;

import jforgame.socket.share.annotation.MessageRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.listener.EventType;
import pers.kinson.im.chat.listener.annotation.EventHandler;
import pers.kinson.im.chat.logic.redpoint.message.vo.RedPoint;
import pers.kinson.im.chat.logic.redpoint.res.ResRedPoint;
import pers.kinson.im.chat.logic.redpoint.service.RedPointListener;

import java.util.HashMap;
import java.util.Map;

@Controller
@MessageRoute
public class RedPointFacade {

//    @Autowired
//    private SearchService searchService;
//
//    @EventHandler(value = {EventType.LOGIN})
//    public void onUserLogin(UserLoginEvent loginEvent) {
//        long userId = loginEvent.getUserId();
//        User user = SpringContext.getUserService().getOnlineUser(userId);
//        Map<Integer, RedPoint> points = new HashMap<>();
//        SpringContext.getBeansOfType(RedPointListener.class).forEach(e->e.register(userId, points));
//        if (!CollectionUtils.isEmpty(points)) {
//            ResRedPoint resRedPoint = new ResRedPoint();
//            resRedPoint.setPoints(points.values().stream().toList());
//            SessionManager.INSTANCE.sendPacketTo(user, resRedPoint);
//        }
//    }
}
