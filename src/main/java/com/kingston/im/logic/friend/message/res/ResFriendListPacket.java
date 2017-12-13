package com.kingston.im.logic.friend.message.res;

import java.util.List;

import com.kingston.im.logic.friend.model.FriendItemVo;
import com.kingston.im.net.IoSession;
import com.kingston.im.net.message.AbstractPacket;
import com.kingston.im.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ResFriendListPacket extends AbstractPacket {

	private List<FriendItemVo> friends;

	@Override
	public PacketType getPacketType() {
		return PacketType.ResFriendList;
	}

	public List<FriendItemVo> getFriends() {
		return friends;
	}

	public void setFriends(List<FriendItemVo> friends) {
		this.friends = friends;
	}

	@Override
	public void execPacket(IoSession session) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeInt(friends.size());
		for (FriendItemVo item:friends) {
			item.writeBody(buf);
		}

	}

	@Override
	public void readBody(ByteBuf buf) {
		// TODO Auto-generated method stub

	}

}
