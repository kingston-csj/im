package pers.kinson.im.chat.logic.discussion.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.discussion.message.vo.DiscussionGroupVo;
import pers.kinson.im.chat.logic.discussion.message.vo.DiscussionMemberVo;
import pers.kinson.im.common.constants.CmdConst;

import java.util.List;

@Data
@MessageMeta(cmd = CmdConst.ResViewDiscussionMembers)
public class ResViewDiscussionMembersList {

    private Long discussionId;

    private List<DiscussionMemberVo> groups;
}
