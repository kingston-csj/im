package pers.kinson.im.chat.logic.search.message.res;

import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.logic.search.message.vo.RecommendFriendItem;
import pers.kinson.im.chat.net.message.AbstractPacket;

import java.util.List;

@Data
public class ResSearchFriends extends AbstractPacket {

	private List<RecommendFriendItem> friends;

	@Override
	public int getPacketId() {
		return CmdConst.ResSearchFriends;
	}

}
