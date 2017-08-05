package com.kingston.dao;

import org.springframework.stereotype.Component;

import com.kingston.model.User;

@Component
public interface UserDao {

	public User findById(Long id);
	
	public void addUser(User user);
	
	public void delUser(Integer id);
	
	public void updateUser(User user);
}
