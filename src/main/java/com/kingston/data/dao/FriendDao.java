package com.kingston.data.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kingston.data.view.FriendView;

@Repository
public interface FriendDao {

	public List<FriendView> getMyFriends(long userId);

}
