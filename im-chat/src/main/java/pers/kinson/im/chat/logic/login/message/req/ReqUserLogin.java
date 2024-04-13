package pers.kinson.im.chat.logic.login.message.req;

import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

@Data
public class ReqUserLogin extends AbstractPacket {

    private long userId;
    private String userPwd;


    @Override
    public int getPacketId() {
        return CmdConst.ReqUserLogin;
    }

}
