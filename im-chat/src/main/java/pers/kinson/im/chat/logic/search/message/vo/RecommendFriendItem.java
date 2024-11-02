package pers.kinson.im.chat.logic.search.message.vo;


import jforgame.socket.share.annotation.MessageMeta;
import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
@MessageMeta(cmd = CmdConst.RecommendFriendVO)
public class RecommendFriendItem {

	private long userId;

	private String nickName;

}