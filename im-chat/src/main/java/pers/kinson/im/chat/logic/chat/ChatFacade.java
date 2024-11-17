package pers.kinson.im.chat.logic.chat;

import jforgame.commons.NumberUtil;
import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.logic.chat.message.req.ReqChatToGroup;
import pers.kinson.im.chat.logic.chat.message.req.ReqChatToUser;
import pers.kinson.im.chat.logic.chat.message.req.ReqFetchNewMessage;
import pers.kinson.im.chat.logic.chat.message.res.ResNewMessage;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;
import pers.kinson.im.chat.logic.search.SearchService;

import java.util.List;

@Component
@MessageRoute
public class ChatFacade {

    @Autowired
    private SearchService searchService;

    @RequestHandler
    public void reqChatToUser(IdSession session, ReqChatToUser req) {
        SpringContext.getChatService().chat(session, req.getToUserId(), req.getContent());
    }

    @RequestHandler
    public void reqChatToGroup(IdSession session, ReqChatToGroup req) {
        Long sender = NumberUtil.longValue(session.getId());
        SpringContext.getChatService().chatToChannel(sender, req.getChannel(), req.getToUserId(), req.getContent());
    }


    @RequestHandler
    public void reqFetchNew(IdSession session, int index,  ReqFetchNewMessage req) {
        Long receiver = NumberUtil.longValue(session.getId());
        List<ChatMessage> chatMessages = SpringContext.getChatService().fetchNewMessage(receiver, req.getChannel(), req.getTopic(), req.getMaxSeq());
        ResNewMessage notify = new ResNewMessage();
        notify.setChannel(req.getChannel());
        notify.setTopic(req.getTopic());
        notify.setMessages(chatMessages);
        session.send(notify);
    }
}
