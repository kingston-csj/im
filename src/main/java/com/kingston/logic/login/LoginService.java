package com.kingston.logic.login;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.base.ServerManager;
import com.kingston.data.dao.UserDao;
import com.kingston.data.model.User;
import com.kingston.logic.login.message.RespUserLoginPacket;
import com.kingston.net.ChannelUtils;
import com.kingston.net.IoSession;

import io.netty.channel.Channel;

@Component
public class LoginService {

	@Autowired
	private UserDao userDao;
	
	public void validateLogin(Channel channel, long userId, String password) {
		User user = validate(userId, password);
		IoSession session = ChannelUtils.getSessionBy(channel);
		RespUserLoginPacket resp = new RespUserLoginPacket();
		if(user != null) {
			resp.setIsValid((byte)1);
			resp.setAlertMsg("登录成功");
			ServerManager.INSTANCE.registerSession(user, session);
		}else{
			resp.setAlertMsg("帐号或密码错误");
		}
	
		ServerManager.INSTANCE.sendPacketTo(session, resp);
	}
	
	/**
	 *  验证帐号密码是否一致
	 */
	private User validate(long userId, String password){
		if (userId <= 0 || StringUtils.isEmpty(password)) {
			return null;
		}
		User user = userDao.findById(userId);
		if (user != null &&
			user.getPassword().equals(password)) {
			return user;
		}
		
		return null;
	}

}
