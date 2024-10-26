package pers.kinson.im.chat.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.kinson.im.chat.data.model.Friends;
import pers.kinson.im.chat.data.view.FriendView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendDao extends BaseMapper<Friends> {

    List<FriendView> getMyFriends(long userId);

}
