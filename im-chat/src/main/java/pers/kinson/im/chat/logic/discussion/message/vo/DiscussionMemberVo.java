package pers.kinson.im.chat.logic.discussion.message.vo;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Getter;
import lombok.Setter;
import pers.kinson.im.common.constants.CmdConst;

@Getter
@Setter
@MessageMeta(cmd = CmdConst.DiscussionGroupMemberVo)
public class DiscussionMemberVo {

    private Long id;

    private Long userId;

    private String nickName;


    /**
     * 职位
     */
    private byte position;

    private byte online;

    /**
     * 头像url
     */
    private String avatar;

}
