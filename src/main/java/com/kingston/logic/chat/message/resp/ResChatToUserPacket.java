package com.kingston.logic.chat.message.resp;

import com.kingston.net.message.AbstractPacket;
import com.kingston.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ResChatToUserPacket extends AbstractPacket {
	
	private String content;

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
		return PacketType.ResChatToUser;
	}

	@Override
	public void execPacket() {
		// TODO Auto-generated method stub
		
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
