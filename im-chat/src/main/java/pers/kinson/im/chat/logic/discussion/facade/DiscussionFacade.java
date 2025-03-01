package pers.kinson.im.chat.logic.discussion.facade;

import jforgame.commons.NumberUtil;
import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.business.entity.User;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.core.CommonResponse;
import pers.kinson.im.chat.listener.EventType;
import pers.kinson.im.chat.listener.annotation.EventHandler;
import pers.kinson.im.chat.logic.discussion.message.req.ReqCreateDiscussion;
import pers.kinson.im.chat.logic.discussion.message.req.ReqJoinDiscussion;
import pers.kinson.im.chat.logic.discussion.message.req.ReqViewDiscussionList;
import pers.kinson.im.chat.logic.discussion.message.req.ReqViewDiscussionMembers;
import pers.kinson.im.chat.logic.discussion.message.res.ResViewDiscussionList;
import pers.kinson.im.chat.logic.discussion.message.res.ResViewDiscussionMembersList;
import pers.kinson.im.chat.logic.discussion.message.vo.DiscussionGroupVo;
import pers.kinson.im.chat.logic.discussion.message.vo.DiscussionMemberVo;
import pers.kinson.im.chat.logic.discussion.service.DiscussionService;
import pers.kinson.im.chat.logic.user.event.UserLoginEvent;

import java.util.List;

@Component
@MessageRoute
public class DiscussionFacade {

    @Autowired
    DiscussionService discussionService;

    @RequestHandler
    public ResViewDiscussionList reqViewGroupList(IdSession session, int index, ReqViewDiscussionList req) {
        Long userId = NumberUtil.longValue(session.getId());
        List<DiscussionGroupVo> discussionGroupVos = discussionService.listAllDiscussion(userId);
        ResViewDiscussionList response = new ResViewDiscussionList();
        response.setGroups(discussionGroupVos);

        return response;
    }

    @RequestHandler
    public ResViewDiscussionMembersList reqViewGroupList(IdSession session, int index, ReqViewDiscussionMembers req) {
        Long userId = NumberUtil.longValue(session.getId());
        List<DiscussionMemberVo> discussionGroupVos = discussionService.listGroupMembers(req.getDiscussionId());
        ResViewDiscussionMembersList response = new ResViewDiscussionMembersList();
        response.setDiscussionId(req.getDiscussionId());
        response.setGroups(discussionGroupVos);

        return response;
    }

    @RequestHandler
    public CommonResponse reqCreate(IdSession session, ReqCreateDiscussion req) {
        Long userId = NumberUtil.longValue(session.getId());
        int code = discussionService.create(userId, req.getMembers(), "随便吧");
        return CommonResponse.valueOf(code);
    }

    @RequestHandler
    public CommonResponse reqJoin(IdSession session, ReqJoinDiscussion req) {
        Long userId = NumberUtil.longValue(session.getId());
        int code = discussionService.join(userId, req.getDiscussionId());
        return CommonResponse.valueOf(code);
    }

    @EventHandler(value = {EventType.LOGIN})
    public void onUserLogin(UserLoginEvent loginEvent) {
        long userId = loginEvent.getUserId();
        User user = SpringContext.getUserService().queryUser(userId);
        List<DiscussionGroupVo> discussionGroupVos = discussionService.listAllDiscussion(userId);
        ResViewDiscussionList response = new ResViewDiscussionList();
        response.setGroups(discussionGroupVos);
        SessionManager.INSTANCE.sendPacketTo(user, response);
    }

}
