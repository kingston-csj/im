package com.kingston.im.logic.login.message.req;

import com.kingston.im.base.SpringContext;
import com.kingston.im.logic.login.LoginService;
import com.kingston.im.net.IoSession;
import com.kingston.im.net.message.AbstractPacket;
import com.kingston.im.net.message.PacketType;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class ReqUserLoginPacket extends AbstractPacket {

	private long userId;
	private String userPwd;

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeLong(userId);
		writeUTF8(buf, userPwd);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.userId = buf.readLong();
		this.userPwd =readUTF8(buf);

		System.err.println("id="+userId+",pwd="+userPwd);
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ReqUserLogin;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public void execPacket(IoSession session) {
		Channel channel = session.getChannel();
		LoginService loginMgr = SpringContext.getBean(LoginService.class);
		loginMgr.validateLogin(channel, getUserId(), getUserPwd());
	}

}
