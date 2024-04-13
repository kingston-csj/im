package pers.kinson.im.chat.logic.user.message.req;

import lombok.Data;
import pers.kinson.im.chat.base.Constants;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

@Data
public class ReqUserRegister extends AbstractPacket {

    private long userId;
    /**
     * 性别{@link Constants#SEX_OF_BOY}
     */
    private byte sex;

    private String nickName;

    private String password;

    @Override
    public int getPacketId() {
        return CmdConst.ReqUserRegister;
    }

}