package pers.kinson.im.chat.logic.login.message.req;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;
@Data
@MessageMeta(cmd = CmdConst.ReqHeartBeat)
public class ReqHeartBeat {

}
