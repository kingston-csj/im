package pers.kinson.im.chat.logic.chat;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jforgame.commons.NumberUtil;
import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.dao.DiscussionMemberDao;
import pers.kinson.im.chat.data.model.DiscussionMember;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.logic.chat.message.req.ReqChatToChannel;
import pers.kinson.im.chat.logic.chat.message.req.ReqFetchNewMessage;
import pers.kinson.im.chat.logic.chat.message.req.ReqMarkNewMessage;
import pers.kinson.im.chat.logic.chat.message.res.ResNewMessage;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;
import pers.kinson.im.chat.logic.search.SearchService;
import pers.kinson.im.common.constants.Channels;

import java.util.List;

@Component
@MessageRoute
public class ChatFacade {

    @Autowired
    private SearchService searchService;

    @Autowired
    DiscussionMemberDao discussionMemberDao;

    @RequestHandler
    public void reqChatToChannel(IdSession session, ReqChatToChannel req) {
        Long sender = NumberUtil.longValue(session.getId());
        SpringContext.getChatService().chatToChannel(sender, req.getChannel(), req.getToUserId(), req.getContentType(), req.getContent());
    }


    @RequestHandler
    public void reqFetchNew(IdSession session, int index, ReqFetchNewMessage req) {
        Long receiver = NumberUtil.longValue(session.getId());
        List<ChatMessage> chatMessages = SpringContext.getChatService().fetchNewMessage(receiver, req.getChannel(), req.getTopic(), req.getMaxSeq());
        ResNewMessage notify = new ResNewMessage();
        notify.setChannel(req.getChannel());
        notify.setTopic(req.getTopic());
        notify.setMessages(chatMessages);
        session.send(notify);
    }

    @RequestHandler
    public void reqMarkNewMessage(IdSession session, ReqMarkNewMessage req) {
        long receiver = NumberUtil.longValue(session.getId());
        if (req.getChannel() == Channels.person) {
            User user = SpringContext.getUserService().getOnlineUser(receiver);
            user.setChatMaxSeq(req.getMaxSeq());
            SpringContext.getUserService().saveUser(user);
        } else if (req.getChannel() == Channels.discussion) {
            DiscussionMember member = discussionMemberDao.selectOne(
                    new LambdaQueryWrapper<DiscussionMember>().eq(DiscussionMember::getUserId, receiver)
                            .eq(DiscussionMember::getDiscussionId, req.getTopic()));
            member.setMaxSeq(req.getMaxSeq());
            discussionMemberDao.updateById(member);
        }
    }
}
