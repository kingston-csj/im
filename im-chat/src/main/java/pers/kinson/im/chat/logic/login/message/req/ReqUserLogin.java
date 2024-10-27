package pers.kinson.im.chat.logic.login.message.req;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;

@Data
@MessageMeta(cmd =  CmdConst.ReqUserLogin)
public class ReqUserLogin {

    private long userId;
    private String userPwd;

}
