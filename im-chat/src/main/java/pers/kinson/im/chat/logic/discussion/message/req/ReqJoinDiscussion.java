package pers.kinson.im.chat.logic.discussion.message.req;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ReqJoinDiscussion)
public class ReqJoinDiscussion {

    private Long discussionId;

}
