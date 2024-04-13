package pers.kinson.im.chat.logic.search.message.req;


import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;

@Data
public class ReqSearchFriends extends AbstractPacket {

	/** 昵称或qq号 */
	private String key;

	@Override
	public int getPacketId() {
		return CmdConst.ReqSearchFriends;
	}

}
