package pers.kinson.im.web.data.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.kinson.business.view.FriendView;

import java.util.List;

@Repository
public interface SearchDao {

    List<FriendView> queryByName(@Param("userName") String userName);

    List<FriendView> queryById(@Param("userId") String userId);
}
