package com.kingston.logic.user.message;

import com.kingston.net.message.AbstractPacket;
import com.kingston.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ReqUserRegisterPacket extends AbstractPacket {
	
	private long userId;
	
	private String nickName;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public void writePacketBody(ByteBuf buf) {
		buf.writeLong(userId);
		writeUTF8(buf, nickName);
	}

	@Override
	public void readPacketBody(ByteBuf buf) {
		this.userId = buf.readLong();
		this.nickName = readUTF8(buf);
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ReqUserRegister;
	}

	@Override
	public void execPacket() {
		// TODO Auto-generated method stub
		
	}
	
	

}
