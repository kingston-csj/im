package pers.kinson.im.chat.logic.friend.message.res;

import io.netty.buffer.ByteBuf;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.logic.friend.message.vo.FriendItemVo;
import pers.kinson.im.chat.net.message.AbstractPacket;

import java.util.List;

public class ResFriendList extends AbstractPacket {

	private List<FriendItemVo> friends;

	@Override
	public int getPacketId() {
		return CmdConst.ResFriendList;
	}

	public List<FriendItemVo> getFriends() {
		return friends;
	}

	public void setFriends(List<FriendItemVo> friends) {
		this.friends = friends;
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
