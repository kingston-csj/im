package com.kingston.im.data.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kingston.im.data.view.FriendView;

@Repository
public interface SearchDao {

	public List<FriendView> queryByName(String userName);
}
