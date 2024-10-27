package pers.kinson.im.chat.logic.friend.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;
import pers.kinson.im.chat.logic.friend.message.vo.FriendItemVo;

import java.util.List;

@Data
@MessageMeta(cmd = CmdConst.ResFriendList)
public class ResFriendList {

	private List<FriendItemVo> friends;


}
