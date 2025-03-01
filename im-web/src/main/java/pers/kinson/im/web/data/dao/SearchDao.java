package pers.kinson.im.web.data.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import pers.kinson.business.view.FriendView;

import java.util.List;

@Repository
public interface SearchDao {

    List<FriendView> queryByName(@Param("userName") String userName);

    List<FriendView> queryById(@Param("userId") String userId);
}
