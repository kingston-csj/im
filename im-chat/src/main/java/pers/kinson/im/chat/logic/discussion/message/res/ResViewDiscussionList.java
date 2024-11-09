package pers.kinson.im.chat.logic.discussion.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.discussion.message.vo.DiscussionGroupVo;
import pers.kinson.im.common.constants.CmdConst;

import java.util.List;

@Data
@MessageMeta(cmd = CmdConst.ResViewDiscussionList)
public class ResViewDiscussionList {

    private List<DiscussionGroupVo> groups;
}
