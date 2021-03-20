package pers.kinson.im.chat.logic.chat.message.res;

import io.netty.buffer.ByteBuf;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

public class ResChatToGroup extends AbstractPacket {
	
	private String content;

	@Override
	public void writeBody(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readBody(ByteBuf buf) {
		// TODO Auto-generated method stub
	}

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
