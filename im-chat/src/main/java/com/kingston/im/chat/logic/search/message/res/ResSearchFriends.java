package com.kingston.im.chat.logic.search.message.res;

import java.util.ArrayList;
import java.util.List;

import com.kingston.im.chat.logic.search.message.vo.RecommendFriendItem;
import com.kingston.im.chat.net.IoSession;
import com.kingston.im.chat.net.message.AbstractPacket;
import com.kingston.im.chat.net.message.PacketType;

import io.netty.buffer.ByteBuf;

public class ResSearchFriends extends AbstractPacket {

	private List<RecommendFriendItem> friends;

	@Override
	public PacketType getPacketType() {
		return PacketType.ResSearchFriends;
	}

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeInt(friends.size());
		for (RecommendFriendItem item : friends) {
			item.writeBody(buf);
		}
	}

	@Override
	public void readBody(ByteBuf buf) {
		int size = buf.readInt();
		this.friends = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			RecommendFriendItem item = new RecommendFriendItem();
			item.readBody(buf);
			friends.add(item);
		}
	}

	public List<RecommendFriendItem> getFriends() {
		return friends;
	}

	public void setFriends(List<RecommendFriendItem> friends) {
		this.friends = friends;
	}

	@Override
	public void execPacket(IoSession session) {
		// TODO Auto-generated method stub

	}

}
