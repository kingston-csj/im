package pers.kinson.im.chat.logic.login.message.res;

import lombok.Data;
import pers.kinson.im.chat.base.Constants;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;


@Data

public class ResUserLogin extends AbstractPacket {

    private String alertMsg;
    private byte isValid;

    public static ResUserLogin valueOfFailed() {
        ResUserLogin response = new ResUserLogin();
        response.setIsValid(Constants.FAILED);
        return response;
    }


    @Override
    public int getPacketId() {
        return CmdConst.ResUserLogin;
    }

}
