package com.kingston.im.chat.logic.login.message.res;

import com.kingston.im.chat.net.IoSession;
import com.kingston.im.chat.net.message.AbstractPacket;
import com.kingston.im.chat.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ResHeartBeat extends AbstractPacket{

	@Override
	public void writeBody(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readBody(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.RespHeartBeat;
	}

	@Override
	public void execPacket(IoSession session) {
		
	}

}
