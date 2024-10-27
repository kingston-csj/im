package pers.kinson.im.chat.logic.chat.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;


@Data
@MessageMeta(cmd = CmdConst.ResChatToUser)
public class ResChatToUser {

    private long fromUserId;

    private String content;

}
