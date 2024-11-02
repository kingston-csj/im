package pers.kinson.im.chat.logic.friend;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jforgame.commons.NumberUtil;
import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.core.HttpResult;
import pers.kinson.im.chat.data.dao.FriendApplyDao;
import pers.kinson.im.chat.data.model.FriendApply;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.logic.friend.message.req.ReqApplyFriend;
import pers.kinson.im.chat.logic.friend.message.req.ReqApplyFriendList;
import pers.kinson.im.chat.logic.friend.message.req.ReqApplyResult;
import pers.kinson.im.chat.logic.friend.message.res.ResApplyFriendList;
import pers.kinson.im.chat.logic.friend.message.vo.FriendApplyVo;
import pers.kinson.im.chat.logic.friend.service.ApplyService;
import pers.kinson.im.chat.logic.friend.service.FriendService;
import pers.kinson.im.common.constants.CommonStatus;

import java.util.List;

@Component
@MessageRoute
public class ApplyFacade {

    @Autowired
    ApplyService applyService;
    @Autowired
    FriendService friendService;
    @Autowired
    FriendApplyDao friendApplyDao;

    @RequestHandler
    public void reqApplyFriendList(IdSession session, ReqApplyFriendList req) {
        Long userId = NumberUtil.longValue(session.getId());
        List<FriendApplyVo> applyVos = applyService.fetchApplyRecords(userId, true);
        ResApplyFriendList response = new ResApplyFriendList();
        response.setRecords(applyVos);
        session.send(response);
    }

    @RequestHandler
    public void reqApplyFriend(IdSession session, ReqApplyFriend req) {
        int code = SpringContext.getBean(ApplyService.class).applyNewFriend(req.getFrom(), req.getTo(), req.getRemark());
        session.send(HttpResult.valueOf(code));
    }

    @RequestHandler
    public void reqApplyResult(IdSession session, ReqApplyResult req) {
        Long userId = NumberUtil.longValue(session.getId());
        FriendApply record = friendApplyDao.selectOne(new LambdaQueryWrapper<FriendApply>().eq(FriendApply::getId, req.getApplyId())
                .eq(FriendApply::getToId, userId));
        if (record == null) {
            return;
        }
        if (req.getStatus() == CommonStatus.APPLY_STATUS_YES) {
            record.setStatus(CommonStatus.APPLY_STATUS_YES);
            int code = friendService.addNewFriend(req.getApplyId(), record.getFromId(), userId);
            User user = SpringContext.getUserService().getOnlineUser(userId);
            friendService.refreshUserFriends(user);
            User other = SpringContext.getUserService().getOnlineUser( record.getFromId());
            if (other != null) {
                friendService.refreshUserFriends(other);
            }
        } else {
            record.setStatus(CommonStatus.APPLY_STATUS_NO);
        }
        friendApplyDao.updateById(record);
    }
}
