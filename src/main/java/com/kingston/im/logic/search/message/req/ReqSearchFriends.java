package com.kingston.im.logic.search.message.req;


import com.kingston.im.base.SpringContext;
import com.kingston.im.net.IoSession;
import com.kingston.im.net.message.AbstractPacket;
import com.kingston.im.net.message.PacketType;

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
