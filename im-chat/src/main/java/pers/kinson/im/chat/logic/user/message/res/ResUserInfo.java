package pers.kinson.im.chat.logic.user.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;
@Data
@MessageMeta(cmd = CmdConst.ResUserInfo)
public class ResUserInfo {

    private long userId;
    /**
     * 账号昵称
     */
    private String userName;
    /**
     * 性别
     */
    private byte sex;
    /**
     * 个性签名
     */
    private String signature;

}
