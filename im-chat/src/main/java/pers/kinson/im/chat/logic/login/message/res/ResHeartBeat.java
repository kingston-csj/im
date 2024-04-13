package pers.kinson.im.chat.logic.login.message.res;

import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

@Data
public class ResHeartBeat extends AbstractPacket {

    @Override
    public int getPacketId() {
        return CmdConst.RespHeartBeat;
    }

}
