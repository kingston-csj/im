package com.kingston.model;

public class User {
	private long userId;
	/** 用户名字 */
	private String  userName;
	/** 密码 */
	private String  authentication;
	
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
	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String password) {
		this.authentication = password;
	}
	
}
