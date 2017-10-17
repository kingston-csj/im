package com.kingston.logic.friend.message;

import java.util.List;

import com.kingston.logic.friend.vo.FriendItemVo;
import com.kingston.net.message.AbstractPacket;
import com.kingston.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class RespFriendListPacket extends AbstractPacket {

	private List<FriendItemVo> friends;

	@Override
	public PacketType getPacketType() {
		return PacketType.RespFriendList;
	}

	public List<FriendItemVo> getFriends() {
		return friends;
	}

	public void setFriends(List<FriendItemVo> friends) {
		this.friends = friends;
	}

	@Override
	public void execPacket() {
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
