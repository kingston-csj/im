package com.kingston.data.model;

import com.kingston.logic.GlobalConst;

public class User {
	
	
	private long userId;
	/** 性别{@link GlobalConst#sex_of_boy} */
	private byte sex;
	/** 用户名字 */
	private String userName;
	/** 密码 */
	private String password;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	
}
