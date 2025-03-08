package pers.kinson.im.chat.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.kinson.business.entity.Friends;
import pers.kinson.business.view.FriendView;

import java.util.List;

@Repository
public interface FriendDao extends BaseMapper<Friends> {

    List<FriendView> getMyFriends(long userId);

}
