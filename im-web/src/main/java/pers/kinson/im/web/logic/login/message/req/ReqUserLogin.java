package pers.kinson.im.web.logic.login.message.req;

import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;
@Data
public class ReqUserLogin {

    private long userId;
    private String userPwd;

}
