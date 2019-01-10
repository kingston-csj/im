package com.kingston.im.chat.data.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kingston.im.chat.data.view.FriendView;

@Repository
public interface FriendDao {

	public List<FriendView> getMyFriends(long userId);

}
