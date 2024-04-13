package pers.kinson.im.chat.logic.chat.message.req;

import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

@Data
public class ReqChatToUser extends AbstractPacket {

	private long toUserId;

	private String content;

	@Override
	public int getPacketId() {
		return CmdConst.ReqChatToUser;
	}

}
