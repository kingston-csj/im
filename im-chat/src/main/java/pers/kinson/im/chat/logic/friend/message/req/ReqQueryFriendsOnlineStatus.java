package pers.kinson.im.chat.logic.friend.message.req;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Getter;
import lombok.Setter;
import pers.kinson.im.common.constants.CmdConst;

@Getter
@Setter
@MessageMeta(cmd = CmdConst.ReqFriendOnlineStatus)
public class ReqQueryFriendsOnlineStatus {

}
