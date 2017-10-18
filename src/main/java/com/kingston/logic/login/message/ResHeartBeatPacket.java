package com.kingston.logic.login.message;

import com.kingston.net.IoSession;
import com.kingston.net.message.AbstractPacket;
import com.kingston.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ResHeartBeatPacket extends AbstractPacket{

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
