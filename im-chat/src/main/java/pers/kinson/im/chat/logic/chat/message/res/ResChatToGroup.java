package pers.kinson.im.chat.logic.chat.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;


@Data
@MessageMeta(cmd = CmdConst.ResChatToGroup)
public class ResChatToGroup {

    private String content;

}
