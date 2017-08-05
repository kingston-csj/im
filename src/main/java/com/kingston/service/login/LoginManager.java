package com.kingston.service.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.kingston.base.ServerDataPool;
import com.kingston.base.ServerManager;
import com.kingston.dao.UserDao;
import com.kingston.model.User;
import com.kingston.net.ChannelUtils;
import com.kingston.net.IoSession;

import io.netty.channel.ChannelHandlerContext;

public enum LoginManager {

	INSTANCE;
	
	@Autowired
	private UserDao userDao;
	
	public void validateLogin(ChannelHandlerContext context, long userId, String password) {
		User user = validate(userId, password);
		ClientLogin resp = new ClientLogin();
		if(user != null) {
			resp.setIsValid((byte)1);
			resp.setAlertMsg("登录成功");
//			ServerManager.INSTANCE.addOnlineContext(userId, context);
			IoSession session = ChannelUtils.getSessionBy(context.channel());
			ServerManager.INSTANCE.registerSession(user, session);
		}else{
			resp.setAlertMsg("帐号或密码错误");
		}
	
		ServerManager.INSTANCE.sendPacketTo(resp, context);
	}
	
	/**
	 *  验证帐号密码是否一致
	 */
	private User validate(long userId, String password){
		userDao = (UserDao) ServerDataPool.SPRING_BEAN_FACTORY.getBean(UserDao.class);
		User user = userDao.findById(userId);
		if (user != null &&
			user.getAuthentication().equals(password)) {
			return user;
		}
		
		return null;
	}

}
