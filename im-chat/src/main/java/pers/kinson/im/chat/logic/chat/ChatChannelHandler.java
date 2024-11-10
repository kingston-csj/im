package pers.kinson.im.chat.logic.chat;

import pers.kinson.im.chat.logic.chat.message.MessageContent;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;

import java.util.Collection;
import java.util.List;

public interface ChatChannelHandler {

    void send(Long senderId, String target, MessageContent content);

    void saveToDb(Long senderId, String target, MessageContent content);

    Collection<Long> receivers(Long senderId, String target);

    List<ChatMessage> pullMessages(Long receiver, String target, long maxSeq);

    /**
     * 频道类型 {@link pers.kinson.im.common.constants.Channels}
     * @return
     */
    byte channelType();
}
