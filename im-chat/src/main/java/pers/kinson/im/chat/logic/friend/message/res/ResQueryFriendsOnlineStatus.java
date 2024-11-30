package pers.kinson.im.chat.logic.friend.message.res;

import jforgame.socket.share.annotation.MessageMeta;
import lombok.Getter;
import lombok.Setter;
import pers.kinson.im.common.constants.CmdConst;

import java.util.List;

@Getter
@Setter
@MessageMeta(cmd = CmdConst.ResFriendOnlineStatus)
public class ResQueryFriendsOnlineStatus {

    private List<Long> ids;
}
