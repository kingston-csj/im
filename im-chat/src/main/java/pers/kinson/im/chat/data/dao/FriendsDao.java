package pers.kinson.im.chat.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.kinson.im.chat.data.model.FriendApply;
import pers.kinson.im.chat.data.model.Friends;

@Repository
public interface FriendsDao extends BaseMapper<Friends> {

}