package com.kingston.im.chat.logic.user.message.req;

import com.kingston.im.chat.base.SpringContext;
import com.kingston.im.chat.logic.user.UserService;
import com.kingston.im.chat.net.IoSession;
import com.kingston.im.chat.net.message.AbstractPacket;
import com.kingston.im.chat.net.message.PacketType;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class ReqUserRegister extends AbstractPacket {

	/** 性别{@link Constants#sex_of_boy} */
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
		UserService userService = SpringContext.getUserService();
		Channel channel = session.getChannel();
		userService.registerNewAccount(channel, getSex(), getNickName(), getPassword());
	}

}