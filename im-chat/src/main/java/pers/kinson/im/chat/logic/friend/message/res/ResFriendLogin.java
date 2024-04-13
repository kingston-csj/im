package pers.kinson.im.chat.logic.friend.message.res;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

/**
 * 好友登录
 *
 * @author kinson
 */
@Data
public class ResFriendLogin extends AbstractPacket {

    private long friendId;

    @Override
    public int getPacketId() {
        return CmdConst.ResFriendLogin;
    }

}
