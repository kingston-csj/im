package com.kingston.service.login;

import io.netty.buffer.ByteBuf;

import com.kingston.net.Packet;
import com.kingston.net.PacketType;

public class ServerHeartBeat extends Packet{

	@Override
	public void writePacketBody(ByteBuf buf) {
	}

	@Override
	public void readPacketBody(ByteBuf buf) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ServerHearBeat;
	}

	@Override
	public void execPacket() {
		System.err.println("收到客户端的pong响应");  
	}

}
