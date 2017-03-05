package com.kingston.service.login;

import io.netty.buffer.ByteBuf;

import com.kingston.net.Packet;
import com.kingston.net.PacketType;

public class ServerHeartBeat extends Packet{

	@Override
	public void writePacketBody(ByteBuf buf) {
//		buf.writeInt(222);
//		buf.writeInt(333);
	}

	@Override
	public void readPacketBody(ByteBuf buf) {
//		System.err.println("args="+buf.readInt());
//		System.err.println("args="+buf.readInt());
		
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
