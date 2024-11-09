package pers.kinson.im.chat.logic.discussion.message.req;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.ReqViewDiscussionMembers)
public class ReqViewDiscussionMembers {

    private Long discussionId;

}
