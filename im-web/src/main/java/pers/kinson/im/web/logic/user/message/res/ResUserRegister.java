package pers.kinson.im.web.logic.user.message.res;

import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
public class ResUserRegister {

    private byte resultCode;

    private String message;

}
