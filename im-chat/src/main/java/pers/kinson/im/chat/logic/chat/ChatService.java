package pers.kinson.im.chat.logic.chat;

import jforgame.commons.NumberUtil;
import jforgame.socket.share.IdSession;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.logic.chat.message.MessageContent;
import pers.kinson.im.chat.logic.chat.message.res.ResChatToUser;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChatService {


    Map<Byte, ChatChannelHandler> handlers = new HashMap<>();

    public void init() {
        SpringContext.getBeansOfType(ChatChannelHandler.class).forEach(e -> handlers.put(e.channelType(), e));
    }

    public void chat(IdSession fromUser, long toUserId, String content) {
        IdSession toUser = SessionManager.INSTANCE.getSessionBy(toUserId);
        if (fromUser == null || toUser == null) {
            return;
        }
        if (!checkDirtyWords(content)) {
            return;
        }

        //双方都推送消息
        ResChatToUser response = new ResChatToUser();
        response.setContent(content);
        response.setFromUserId(NumberUtil.longValue(fromUser.getId()));
        response.setToUserId(toUserId);
        toUser.send(response);

        fromUser.send(response);
    }

    private boolean checkDirtyWords(String content) {
        return true;
    }


    public void chatToChannel(Long sender, byte channel, long target, byte contentType, String content) {
        ChatChannelHandler handler = handlers.get(channel);
        MessageContent chatMessage = SpringContext.getMessageContentFactory().parse(contentType, content);
        chatMessage.setType(contentType);
        handler.send(sender, target, chatMessage);
    }

    public List<ChatMessage> fetchNewMessage(Long receiver, byte channel, long target, long maxSeq) {
        ChatChannelHandler handler = handlers.get(channel);
        return handler.pullMessages(receiver, target, maxSeq);
    }

}
