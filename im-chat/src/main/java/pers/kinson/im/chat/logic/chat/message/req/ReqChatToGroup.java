package pers.kinson.im.chat.logic.chat.message.req;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

@Data
public class ReqChatToGroup extends AbstractPacket {
	
	private long toUserId;
	
	private String content;
	
	@Override
	public int getPacketId() {
		return CmdConst.ReqChatToGroup;
	}

}
