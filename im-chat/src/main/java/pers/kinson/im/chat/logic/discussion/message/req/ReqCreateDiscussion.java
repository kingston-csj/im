package pers.kinson.im.chat.logic.discussion.message.req;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

import java.util.List;

@Data
@MessageMeta(cmd = CmdConst.ReqCreateDiscussion)
public class ReqCreateDiscussion {

    private List<Long> members;

}
