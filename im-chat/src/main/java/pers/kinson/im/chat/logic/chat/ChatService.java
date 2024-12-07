package pers.kinson.im.chat.logic.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.dao.MessageDao;
import pers.kinson.im.chat.data.model.Message;
import pers.kinson.im.chat.logic.chat.message.MessageContent;
import pers.kinson.im.chat.logic.chat.message.res.ResModifyMessage;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;
import pers.kinson.im.common.logger.LoggerUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ChatService {

    @Autowired
    MessageDao messageDao;

    Map<Byte, ChatChannelHandler> handlers = new HashMap<>();

    public void init() {
        SpringContext.getBeansOfType(ChatChannelHandler.class).forEach(e -> handlers.put(e.channelType(), e));
    }


    @Scheduled(cron = "0 59 23 * * ?")
    private void checkExpired() {
        int removed = messageDao.clearExpiredMessage();
        if (removed > 0) {
            log.info("清除{}条记录", removed);
        }
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

    public void refreshMessage(Message message) {
        if (message == null) {
            return;
        }
        ChatChannelHandler handler = handlers.get(message.getChannel());
        Collection<Long> receivers = handler.receivers(message.getSender(), message.getReceiver());
        ResModifyMessage modifyMessage = new ResModifyMessage();
        modifyMessage.setTopic(message.getReceiver());
        modifyMessage.setChannel(message.getChannel());
        modifyMessage.setMessage(handler.decorate(message));
        receivers.forEach(e -> SessionManager.INSTANCE.sendPacketTo(e, modifyMessage));
    }

}
