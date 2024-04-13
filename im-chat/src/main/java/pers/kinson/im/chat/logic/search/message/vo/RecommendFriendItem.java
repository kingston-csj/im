package pers.kinson.im.chat.logic.search.message.vo;


import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.net.message.AbstractPacket;
import pers.kinson.im.chat.net.message.ByteBufBean;

import io.netty.buffer.ByteBuf;

@Data
public class RecommendFriendItem extends AbstractPacket {

	private long userId;

	private String nickName;

	@Override
	public int getPacketId() {
		return CmdConst.RecommendFriendVO;
	}
}