package pers.kinson.im.chat.logic.search.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.logic.search.message.vo.RecommendFriendItem;

import java.util.List;

@Data
@MessageMeta(cmd = CmdConst.ResSearchFriends)
public class ResSearchFriends {

	private List<RecommendFriendItem> friends;

}
