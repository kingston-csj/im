package pers.kinson.im.chat.logic.login.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CommonStatus;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd =  CmdConst.ResUserLogin)
public class ResUserLogin   {

    private String alertMsg;
    private byte isValid;

    public static ResUserLogin valueOfFailed() {
        ResUserLogin response = new ResUserLogin();
        response.setIsValid(CommonStatus.FAILED);
        return response;
    }

}
