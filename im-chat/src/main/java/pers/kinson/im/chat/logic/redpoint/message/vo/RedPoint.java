package pers.kinson.im.chat.logic.redpoint.message.vo;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Builder;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;
@Data
@MessageMeta(cmd = CmdConst.RedPointVo)
@Builder
public class RedPoint {

    private int id;
    /**
     * 数量
     */
    private int count;
}
