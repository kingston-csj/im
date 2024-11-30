package pers.kinson.im.chat.logic.file.message.req;


import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ReqOnlineFileFinish)
public class ReqOnlineTransferFileFinish {


    private Long messageId;

}
