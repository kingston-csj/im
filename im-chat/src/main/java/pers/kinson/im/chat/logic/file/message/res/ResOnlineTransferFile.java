package pers.kinson.im.chat.logic.file.message.res;


import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ResOnlineFileApply)
public class ResOnlineTransferFile {

    private String requestId;
}
