package pers.kinson.im.chat.logic.redpoint.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.redpoint.message.vo.RedPoint;

import java.util.List;
import pers.kinson.im.common.constants.CmdConst;
@Data
@MessageMeta(cmd = CmdConst.ResRedPoint)
public class ResRedPoint {

    private List<RedPoint> points;
}
