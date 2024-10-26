package pers.kinson.im.chat.logic.friend.message.req;

import lombok.Getter;
import lombok.Setter;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

@Getter
@Setter
public class ReqApplyFriend extends AbstractPacket  {

    private Long from;

    private Long to;

    private String remark;

    @Override
    public int getPacketId() {
        return CmdConst.ReqApplyFriend;
    }
}
