package pers.kinson.im.chat.logic.friend.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;
@Data
@MessageMeta(cmd = CmdConst.ResApplyFriend)
public class ResApplyFriend {

    /**
     * 0表示申请成功
     */
    private int code;
}
