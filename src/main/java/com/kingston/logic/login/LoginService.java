package com.kingston.logic.login;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.base.ServerManager;
import com.kingston.data.dao.UserDao;
import com.kingston.data.model.User;
import com.kingston.logic.friend.FriendService;
import com.kingston.logic.friend.message.ResFriendListPacket;
import com.kingston.logic.friend.vo.FriendItemVo;
import com.kingston.logic.login.message.ResUserLoginPacket;
import com.kingston.net.ChannelUtils;
import com.kingston.net.IoSession;

import io.netty.channel.Channel;

@Component
public class LoginService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private FriendService friendService;

	public void validateLogin(Channel channel, long userId, String password) {
		User user = validate(userId, password);
		IoSession session = ChannelUtils.getSessionBy(channel);
		ResUserLoginPacket resp = new ResUserLoginPacket();
		if(user == null) {
			resp.setAlertMsg("帐号或密码错误");
			ServerManager.INSTANCE.sendPacketTo(session, resp);
			return;
		}

		onLoginSucc(user, session);
	}

	private void onLoginSucc(User user, IoSession session) {
		ResUserLoginPacket loginPact = new ResUserLoginPacket();
		loginPact.setIsValid((byte)1);
		loginPact.setAlertMsg("登录成功");
		ServerManager.INSTANCE.registerSession(user, session);
		ServerManager.INSTANCE.sendPacketTo(session, loginPact);

		List<FriendItemVo> myFriends = friendService.listMyFriends(user.getUserId());
		ResFriendListPacket friendsPact = new ResFriendListPacket();
		friendsPact.setFriends(myFriends);
		ServerManager.INSTANCE.sendPacketTo(session, friendsPact);
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
