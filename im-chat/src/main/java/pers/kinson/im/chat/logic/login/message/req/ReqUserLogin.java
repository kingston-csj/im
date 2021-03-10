package pers.kinson.im.chat.logic.login.message.req;

import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.logic.login.LoginService;
import pers.kinson.im.chat.net.IoSession;
import pers.kinson.im.chat.net.message.AbstractPacket;
import pers.kinson.im.chat.net.message.PacketType;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class ReqUserLogin extends AbstractPacket {

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
