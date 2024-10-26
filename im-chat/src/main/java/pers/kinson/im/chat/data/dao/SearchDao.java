package pers.kinson.im.chat.data.dao;

import pers.kinson.im.chat.data.view.FriendView;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Repository
public interface SearchDao {

    List<FriendView> queryByName(@Param("userName") String userName);

    List<FriendView> queryById(@Param("userId") String userId);
}
