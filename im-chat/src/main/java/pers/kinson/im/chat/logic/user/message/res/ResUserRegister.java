package pers.kinson.im.chat.logic.user.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ResUserRegister)
public class ResUserRegister {

    private byte resultCode;

    private String message;

}
