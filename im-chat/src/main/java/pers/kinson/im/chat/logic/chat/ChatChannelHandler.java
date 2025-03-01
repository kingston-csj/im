package pers.kinson.im.chat.logic.chat;

import pers.kinson.business.entity.Message;
import pers.kinson.im.chat.logic.chat.message.MessageContent;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;

import java.util.Collection;
import java.util.List;

public interface ChatChannelHandler {

    void send(Long senderId, Long target, MessageContent content);

    void saveToDb(Long senderId, Long target, MessageContent content);

    Collection<Long> receivers(Long senderId, Long target);

    List<ChatMessage> pullMessages(Long receiver, Long target, long maxSeq);

    /**
     * 频道类型 {@link pers.kinson.im.common.constants.Channels}
     * @return
     */
    byte channelType();

    ChatMessage decorate(Message message);

}
