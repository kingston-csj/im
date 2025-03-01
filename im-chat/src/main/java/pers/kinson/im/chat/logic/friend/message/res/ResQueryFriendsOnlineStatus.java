package pers.kinson.im.chat.logic.friend.message.res;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResQueryFriendsOnlineStatus {

    private List<Long> ids;
}
