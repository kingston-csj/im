package com.kingston.im.logic.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.im.base.Constants;
import com.kingston.im.base.SessionManager;
import com.kingston.im.base.SpringContext;
import com.kingston.im.data.model.User;
import com.kingston.im.listener.EventType;
import com.kingston.im.logic.login.event.UserLoginEvent;
import com.kingston.im.logic.login.message.res.ResUserLoginPacket;
import com.kingston.im.logic.user.UserService;
import com.kingston.im.net.ChannelUtils;
import com.kingston.im.net.IoSession;

import io.netty.channel.Channel;

@Component
public class LoginService {

	@Autowired
	private UserService userService;

	public void validateLogin(Channel channel, long userId, String password) {
		User user = userService.queryUser(userId, password);
		IoSession session = ChannelUtils.getSessionBy(channel);
		if (user == null) {
			SessionManager.INSTANCE.sendPacketTo(session,
					ResUserLoginPacket.valueOfFailed());
			return;
		}

		onLoginSucc(user, session);
	}

	private void onLoginSucc(User user, IoSession session) {
		SessionManager.INSTANCE.registerSession(user, session);
		userService.addUser2Online(user);

		ResUserLoginPacket loginPact = new ResUserLoginPacket();
		loginPact.setIsValid(Constants.TRUE);
		SessionManager.INSTANCE.sendPacketTo(session, loginPact);

		SpringContext.getEventDispatcher().fireEvent(
				new UserLoginEvent(EventType.LOGIN, user.getUserId()));
	}

}
