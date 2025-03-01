package pers.kinson.im.web.logic.search.message.res;

import lombok.Data;
import pers.kinson.im.web.logic.search.message.vo.RecommendFriendItem;

import java.util.List;
import pers.kinson.im.common.constants.CmdConst;

@Data
public class ResSearchFriends {

	private List<RecommendFriendItem> friends;

}
