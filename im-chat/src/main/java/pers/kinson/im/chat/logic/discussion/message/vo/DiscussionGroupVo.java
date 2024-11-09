package pers.kinson.im.chat.logic.discussion.message.vo;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.DiscussionGroupVo)
public class DiscussionGroupVo {

    private Long id;

    private String name;
}
