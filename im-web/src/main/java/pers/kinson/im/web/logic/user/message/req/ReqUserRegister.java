package pers.kinson.im.web.logic.user.message.req;

import lombok.Data;
import pers.kinson.im.common.constants.CommonStatus;
import pers.kinson.im.common.constants.CmdConst;
@Data
public class ReqUserRegister {

    private long userId;
    /**
     * 性别{@link CommonStatus#SEX_OF_BOY}
     */
    private byte sex;

    private String nickName;

    private String password;

}