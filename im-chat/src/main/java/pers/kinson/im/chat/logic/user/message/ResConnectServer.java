package pers.kinson.im.chat.logic.user.message;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ResUserLogin)
public class ResConnectServer {

    private byte status;
}
