package pers.kinson.im.chat.logic.friend.handler;

import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.core.HttpResult;
import pers.kinson.im.chat.dispatch.MessageHandler;
import pers.kinson.im.chat.logic.friend.ApplyService;
import pers.kinson.im.chat.logic.friend.message.req.ReqApplyFriend;
import pers.kinson.im.chat.logic.search.message.req.ReqSearchFriends;
import pers.kinson.im.chat.net.IoSession;

public class ApplyFriendHandler extends MessageHandler<ReqApplyFriend> {

    @Override
    public void action(IoSession session, ReqApplyFriend req) {
        int code = SpringContext.getBean(ApplyService.class).applyNewFriend(req.getFrom(), req.getTo(), req.getRemark());
        session.sendPacket(HttpResult.valueOf(code));
    }
}
