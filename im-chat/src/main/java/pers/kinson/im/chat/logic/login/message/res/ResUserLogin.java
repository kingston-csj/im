package pers.kinson.im.chat.logic.login.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.base.Constants;
import pers.kinson.im.chat.logic.CmdConst;


@Data
@MessageMeta(cmd =  CmdConst.ResUserLogin)
public class ResUserLogin   {

    private String alertMsg;
    private byte isValid;

    public static ResUserLogin valueOfFailed() {
        ResUserLogin response = new ResUserLogin();
        response.setIsValid(Constants.FAILED);
        return response;
    }

}
