package pers.kinson.im.chat.logic.chat.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ResModifyMessage)
public class ResModifyMessage {

    private byte channel;

    private long topic;

    private ChatMessage message;
}
