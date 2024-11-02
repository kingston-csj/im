package pers.kinson.im.chat.logic.friend.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Getter;
import lombok.Setter;
import pers.kinson.im.common.constants.CmdConst;
@Getter
@Setter
@MessageMeta(cmd = CmdConst.ResApplyResult)
public class ResApplyResult {
    private byte status;

    private Long fromId;
    private Long targetId;

    private String name;
}
