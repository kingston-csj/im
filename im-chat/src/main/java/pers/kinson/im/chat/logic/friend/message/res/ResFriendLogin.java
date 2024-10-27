package pers.kinson.im.chat.logic.friend.message.res;


import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.chat.logic.CmdConst;

/**
 * 好友登录
 */
@Data
@MessageMeta(cmd = CmdConst.ResFriendLogin)
public class ResFriendLogin {

    private long friendId;

}
