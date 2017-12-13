package com.kingston.im.logic.login;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.im.base.Constants;
import com.kingston.im.base.ServerManager;
import com.kingston.im.data.dao.UserDao;
import com.kingston.im.data.model.User;
import com.kingston.im.logic.friend.FriendService;
import com.kingston.im.logic.login.message.res.ResUserLoginPacket;
import com.kingston.im.logic.user.UserService;
import com.kingston.im.net.ChannelUtils;
import com.kingston.im.net.IoSession;

import io.netty.channel.Channel;

@Component
public class LoginService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private FriendService friendService;
	@Autowired
	private UserService userService;

	public void validateLogin(Channel channel, long userId, String password) {
		User user = validate(userId, password);
		IoSession session = ChannelUtils.getSessionBy(channel);
		ResUserLoginPacket resp = new ResUserLoginPacket();
		if(user == null) {
			resp.setIsValid(Constants.FALSE);
			ServerManager.INSTANCE.sendPacketTo(session, resp);
			return;
		}

		onLoginSucc(user, session);
	}

	private void onLoginSucc(User user, IoSession session) {
		ServerManager.INSTANCE.registerSession(user, session);
		userService.addUser2Online(user.getUserId());

		ResUserLoginPacket loginPact = new ResUserLoginPacket();
		loginPact.setIsValid(Constants.TRUE);
		ServerManager.INSTANCE.sendPacketTo(session, loginPact);

		userService.refreshUserProfile(user);

		friendService.refreshUserFriends(user);
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
