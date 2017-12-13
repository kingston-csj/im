package com.kingston.im.logic.friend.message.res;

import com.kingston.im.net.IoSession;
import com.kingston.im.net.message.AbstractPacket;
import com.kingston.im.net.message.PacketType;

import io.netty.buffer.ByteBuf;

/**
 * 好友注销
 * @author kingston
 */
public class ResFriendLogoutPacket extends AbstractPacket {

	private long friendId;

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ResFriendLogout;
	}

	@Override
	public void execPacket(IoSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeLong(friendId);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.friendId = buf.readLong();
	}

}
