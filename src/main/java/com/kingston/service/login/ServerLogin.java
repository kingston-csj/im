package com.kingston.service.login;

import io.netty.buffer.ByteBuf;

import com.kingston.net.Packet;
import com.kingston.net.PacketType;

public class ServerLogin  extends Packet{

	private byte reqType;
	private int userId;
	private  String userName;
	private  String userPwd; 

	@Override
	public void writePacketBody(ByteBuf buf) {
		buf.writeInt(userId);
		writeUTF8(buf, userPwd);
	}

	@Override
	public void readPacketBody(ByteBuf buf) {
		this.userId = buf.readInt();
		this.userPwd =readUTF8(buf);

		System.err.println("id="+userId+",pwd="+userPwd);
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ServerLogin;
	}

	@Override
	public void execPacket() {

		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public byte getReqType() {
		return reqType;
	}

	public void setReqType(byte reqType) {
		this.reqType = reqType;
	}

}
