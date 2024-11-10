package pers.kinson.im.chat.logic.discussion.message.res;

import lombok.Data;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;

import java.util.List;

@Data
public class ResNotifyMessage {

    private Long discussionId;

    private List<ChatMessage> messages;
}
