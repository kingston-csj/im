package com.kingston.im.logic.user.message.req;

import com.kingston.im.logic.GlobalConst;
import com.kingston.im.net.IoSession;
import com.kingston.im.net.message.AbstractPacket;
import com.kingston.im.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ReqUserRegisterPacket extends AbstractPacket {
	
	/** 性别{@link GlobalConst#sex_of_boy} */
	private byte sex;
	
	private String nickName;
	
	private String password;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeByte(sex);
		writeUTF8(buf, nickName);
		writeUTF8(buf, password);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.sex = buf.readByte();
		this.nickName = readUTF8(buf);
		this.password = readUTF8(buf);
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ReqUserRegister;
	}

	@Override
	public void execPacket(IoSession session) {
		// TODO Auto-generated method stub
		
	}

}