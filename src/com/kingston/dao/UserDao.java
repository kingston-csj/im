package com.kingston.dao;

import com.kingston.model.User;


public interface UserDao {

	public User findById(Integer id);
	
	public void addUser(User user);
	
	public void delUser(Integer id);
	
	public void updateUser(User user);
}
