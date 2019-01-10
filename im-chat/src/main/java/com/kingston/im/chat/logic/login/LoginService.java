package com.kingston.im.chat.logic.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.im.chat.base.Constants;
import com.kingston.im.chat.base.SessionManager;
import com.kingston.im.chat.base.SpringContext;
import com.kingston.im.chat.data.model.User;
import com.kingston.im.chat.listener.EventType;
import com.kingston.im.chat.logic.login.event.UserLoginEvent;
import com.kingston.im.chat.logic.login.message.res.ResUserLogin;
import com.kingston.im.chat.logic.user.UserService;
import com.kingston.im.chat.net.ChannelUtils;
import com.kingston.im.chat.net.IoSession;

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
					ResUserLogin.valueOfFailed());
			return;
		}

		onLoginSucc(user, session);
	}

	private void onLoginSucc(User user, IoSession session) {
		SessionManager.INSTANCE.registerSession(user, session);
		userService.addUser2Online(user);

		ResUserLogin loginPact = new ResUserLogin();
		loginPact.setIsValid(Constants.TRUE);
		SessionManager.INSTANCE.sendPacketTo(session, loginPact);

		SpringContext.getEventDispatcher().fireEvent(
				new UserLoginEvent(EventType.LOGIN, user.getUserId()));
	}

}
