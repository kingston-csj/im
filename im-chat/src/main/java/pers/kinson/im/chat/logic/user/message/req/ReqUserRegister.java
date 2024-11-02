package pers.kinson.im.chat.logic.user.message.req;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CommonStatus;
import pers.kinson.im.common.constants.CmdConst;
@Data
@MessageMeta(cmd = CmdConst.ReqUserRegister)
public class ReqUserRegister {

    private long userId;
    /**
     * 性别{@link CommonStatus#SEX_OF_BOY}
     */
    private byte sex;

    private String nickName;

    private String password;

}