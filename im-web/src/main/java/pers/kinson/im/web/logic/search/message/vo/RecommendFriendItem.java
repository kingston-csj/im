package pers.kinson.im.web.logic.search.message.vo;


import lombok.Data;
import pers.kinson.im.common.constants.CmdConst;

@Data
public class RecommendFriendItem {

	private long userId;

	private String nickName;

}