package pers.kinson.im.chat.logic.user.message;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ReqUserLogin)
public class ReqConnectSocket {

    private long userId;

    private String token;
}
