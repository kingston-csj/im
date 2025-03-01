package pers.kinson.im.chat.logic.friend.message.res;

import lombok.Data;
import pers.kinson.im.chat.logic.friend.message.vo.FriendApplyVo;

import java.util.List;

@Data
public class ResApplyFriendList {

    List<FriendApplyVo> records;
}
