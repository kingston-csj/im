package pers.kinson.im.chat.logic.friend.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;

/**
 * 好友注销
 */
@Data
@MessageMeta(cmd = CmdConst.ResFriendLogout)
public class ResFriendLogout {

	private long friendId;

}
