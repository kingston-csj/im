package com.kingston.service.login;

import io.netty.channel.ChannelHandlerContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.kingston.ball.SyncPosManager;
import com.kingston.base.ServerManager;
import com.kingston.dao.UserDao;
import com.kingston.model.User;

public class LoginManagerImpl implements LoginManager{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void validateLogin(ChannelHandlerContext context,Integer userId, String password) {
		boolean isValid = validate(userId, password);
		ClientLogin resp = new ClientLogin();
		if(isValid){
			resp.setIsValid((byte)1);
			resp.setAlertMsg("µ«¬º≥…π¶");
			ServerManager.INSTANCE.addOnlineContext(userId, context);
		}else{
			resp.setAlertMsg("’ ∫≈ªÚ√‹¬Î¥ÌŒÛ");
		}
	
		ServerManager.INSTANCE.sendPacketTo(resp, context);
	}
	
	/**
	 *  —È÷§’ ∫≈√‹¬Î «∑Ò“ª÷¬
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
