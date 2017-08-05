package com.kingston.logic.login.message;

import com.kingston.net.message.Packet;
import com.kingston.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ClientHeartBeat extends Packet{

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
		return PacketType.ClientHeartBeat;
	}

	@Override
	public void execPacket() {
		
	}

}
