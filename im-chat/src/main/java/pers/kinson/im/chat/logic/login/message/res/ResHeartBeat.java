package pers.kinson.im.chat.logic.login.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.RespHeartBeat)
public class ResHeartBeat {

}
