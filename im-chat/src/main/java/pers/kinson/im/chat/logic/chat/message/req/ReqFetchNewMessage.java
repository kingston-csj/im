package pers.kinson.im.chat.logic.chat.message.req;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ReqFetchNewMessage)
public class ReqFetchNewMessage {

    private byte channel;

    private String topic;

    private long maxSeq;

}
