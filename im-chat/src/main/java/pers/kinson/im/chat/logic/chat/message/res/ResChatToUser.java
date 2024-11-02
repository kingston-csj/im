package pers.kinson.im.chat.logic.chat.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;


@Data
@MessageMeta(cmd = CmdConst.ResChatToUser)
public class ResChatToUser {

    private Long fromUserId;

    private Long toUserId;

    private String content;

}
