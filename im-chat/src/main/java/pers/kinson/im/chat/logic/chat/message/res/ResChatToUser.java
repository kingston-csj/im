package pers.kinson.im.chat.logic.chat.message.res;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;


@Data
public class ResChatToUser extends AbstractPacket {

    private long fromUserId;

    private String content;

    @Override
    public int getPacketId() {
        return CmdConst.ResChatToUser;
    }

    @Override
    public String toString() {
        return "ResChatToUserPacket [fromUserId=" + fromUserId + ", content=" + content + "]";
    }

}
