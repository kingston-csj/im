package com.kingston.logic.login.message;

import com.kingston.net.message.AbstractPacket;
import com.kingston.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class RespHeartBeatPacket extends AbstractPacket{

	@Override
	public void writePacketBody(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readPacketBody(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.RespHeartBeat;
	}

	@Override
	public void execPacket() {
		
	}

}
