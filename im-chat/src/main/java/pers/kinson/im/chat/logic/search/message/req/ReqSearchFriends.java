package pers.kinson.im.chat.logic.search.message.req;


import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.net.IoSession;
import pers.kinson.im.chat.net.message.AbstractPacket;
import pers.kinson.im.chat.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ReqSearchFriends extends AbstractPacket {

	/** 昵称或qq号 */
	private String key;

	@Override
	public PacketType getPacketType() {
		return PacketType.ReqSearchFriends;
	}

	public void writeBody(ByteBuf buf) {
		writeUTF8(buf, key);
	}

	public void readBody(ByteBuf buf) {
		this.key = readUTF8(buf);
	}

	@Override
	public void execPacket(IoSession session) {
		SpringContext.getSearchService().search(session, key);
	}

}
