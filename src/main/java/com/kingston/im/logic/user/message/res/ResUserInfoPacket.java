package com.kingston.im.logic.user.message.res;

import com.kingston.im.net.IoSession;
import com.kingston.im.net.message.AbstractPacket;
import com.kingston.im.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ResUserInfoPacket extends AbstractPacket {

	private long userId;
	/** 账号昵称 */
	private String userName;
	/** 性别 */
	private byte sex;
	/** 个性签名　*/
	private String signature;

	@Override
	public PacketType getPacketType() {
		return PacketType.ResUserInfo;
	}

	@Override
	public void execPacket(IoSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeLong(userId);
		writeUTF8(buf, userName);
		buf.writeByte(sex);
		writeUTF8(buf, signature);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.userId = buf.readLong();
		this.userName = readUTF8(buf);
		this.sex = buf.readByte();
		this.signature = readUTF8(buf);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "ResUserInfoMessage [userId=" + userId + ", userName=" + userName + ", signature=" + signature + ", sex="
				+ sex + "]";
	}

}
