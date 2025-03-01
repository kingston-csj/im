package pers.kinson.im.web.logic.login.message.res;

import lombok.Data;
import pers.kinson.im.common.constants.CommonStatus;
import pers.kinson.im.common.constants.CmdConst;

@Data
public class ResUserLogin   {

    private String alertMsg;
    private byte isValid;

    public static ResUserLogin valueOfFailed() {
        ResUserLogin response = new ResUserLogin();
        response.setIsValid(CommonStatus.FAILED);
        return response;
    }

}
