package pers.kinson.im.chat.logic.login.message.req;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

@Data
public class ReqHeartBeat extends AbstractPacket {

    @Override
    public int getPacketId() {
        return CmdConst.ReqHeartBeat;
    }

}
