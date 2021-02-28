package com.kingston.im.chat.data.dao;

import com.kingston.im.chat.data.view.FriendView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchDao {

    List<FriendView> queryByName(String userName);
}
