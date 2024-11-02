package pers.kinson.im.chat.logic.chat.message.req;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ReqChatToUser)
public class ReqChatToUser {

    private long toUserId;

    private String content;

}
