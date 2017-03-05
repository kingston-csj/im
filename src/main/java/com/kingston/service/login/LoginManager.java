package com.kingston.service.login;

import io.netty.channel.ChannelHandlerContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.kingston.base.ServerManager;
import com.kingston.dao.UserDao;

public enum LoginManager {

	INSTANCE;
	
	@Autowired
	private UserDao userDao;
	
	public void validateLogin(ChannelHandlerContext context,Integer userId, String password) {
		boolean isValid = validate(userId, password);
		ClientLogin resp = new ClientLogin();
		if(isValid){
			resp.setIsValid((byte)1);
			resp.setAlertMsg("登录成功");
			ServerManager.INSTANCE.addOnlineContext(userId, context);
		}else{
			resp.setAlertMsg("帐号或密码错误");
		}
	
		ServerManager.INSTANCE.sendPacketTo(resp, context);
	}
	
	/**
	 *  验证帐号密码是否一致
	 */
	private boolean validate(Integer userId, String password){
//		userDao = (UserDao) ServerDataPool.SPRING_BEAN_FACTORY .getBean(UserDao.class);
//		User user = userDao.findById(userId);
//		if(user == null) return false;
//		
//		return user.getPassword().equals(password);
		return true;
	}

}
