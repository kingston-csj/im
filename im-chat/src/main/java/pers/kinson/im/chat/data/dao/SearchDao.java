package pers.kinson.im.chat.data.dao;

import pers.kinson.im.chat.data.view.FriendView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchDao {

    List<FriendView> queryByName(String userName);
}
