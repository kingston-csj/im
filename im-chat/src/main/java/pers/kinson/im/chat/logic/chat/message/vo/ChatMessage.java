package pers.kinson.im.chat.logic.chat.message.vo;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pers.kinson.im.chat.logic.chat.message.MessageContent;
import pers.kinson.im.common.constants.CmdConst;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MessageMeta(cmd = CmdConst.ChatMessageVo)
public class ChatMessage {

    private long id;

    private long senderId;

    private String senderName;

    private long receiverId;

    private String receiverName;

    private byte type;
    private String json;

    private String date;

    private transient MessageContent content;
}
