package com.kingston.logic.login.message;

import com.kingston.net.message.AbstractPacket;
import com.kingston.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ReqHeartBeatPacket extends AbstractPacket{

	@Override
	public void writePacketBody(ByteBuf buf) {
	}

	@Override
	public void readPacketBody(ByteBuf buf) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ReqHeartBeat;
	}

	@Override
	public void execPacket() {
		System.err.println("收到客户端的pong响应");  
	}

}
