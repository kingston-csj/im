package pers.kinson.im.chat.logic.chat.message.req;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ReqMarkNewMessage)
public class ReqMarkNewMessage {

    private byte channel;

    private long topic;

    private long maxSeq;

}
