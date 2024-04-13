package pers.kinson.im.chat.logic.friend.message.res;

import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.logic.friend.message.vo.FriendItemVo;
import pers.kinson.im.chat.net.message.AbstractPacket;

import java.util.List;

@Data
public class ResFriendList extends AbstractPacket {

	private List<FriendItemVo> friends;

	@Override
	public int getPacketId() {
		return CmdConst.ResFriendList;
	}


}
