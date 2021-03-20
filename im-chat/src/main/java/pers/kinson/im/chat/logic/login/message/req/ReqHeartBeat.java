package pers.kinson.im.chat.logic.login.message.req;

import io.netty.buffer.ByteBuf;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

public class ReqHeartBeat extends AbstractPacket {

	@Override
	public void writeBody(ByteBuf buf) {
	}

	@Override
	public void readBody(ByteBuf buf) {

	}

	@Override
	public int getPacketId() {
		return CmdConst.ReqHeartBeat;
	}

}
