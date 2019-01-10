package com.kingston.im.chat.logic.login.message.req;

import com.kingston.im.chat.net.IoSession;
import com.kingston.im.chat.net.message.AbstractPacket;
import com.kingston.im.chat.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ReqHeartBeat extends AbstractPacket {

	@Override
	public void writeBody(ByteBuf buf) {
	}

	@Override
	public void readBody(ByteBuf buf) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ReqHeartBeat;
	}

	@Override
	public void execPacket(IoSession session) {
		System.err.println("收到客户端的心跳包");
	}

}
