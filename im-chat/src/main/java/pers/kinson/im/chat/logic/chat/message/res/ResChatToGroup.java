package pers.kinson.im.chat.logic.chat.message.res;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;


@Data
public class ResChatToGroup extends AbstractPacket {
	
	private String content;

	@Override
	public int getPacketId() {
		return CmdConst.ResChatToUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
