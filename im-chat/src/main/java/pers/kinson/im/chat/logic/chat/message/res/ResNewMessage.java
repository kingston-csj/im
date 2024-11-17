package pers.kinson.im.chat.logic.chat.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;
import pers.kinson.im.common.constants.CmdConst;

import java.util.List;

@Data
@MessageMeta(cmd = CmdConst.ResNewMessage)
public class ResNewMessage {

    private byte channel;

    private String topic;

    private List<ChatMessage> messages;
}
