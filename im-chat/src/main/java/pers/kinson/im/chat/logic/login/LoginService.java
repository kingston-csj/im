package pers.kinson.im.chat.logic.login;

import jforgame.socket.share.IdSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.kinson.im.common.constants.CommonStatus;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.listener.EventType;
import pers.kinson.im.chat.logic.login.event.UserLoginEvent;
import pers.kinson.im.chat.logic.login.message.res.ResUserLogin;
import pers.kinson.im.chat.logic.user.UserService;
import pers.kinson.im.chat.net.ChannelUtils;

import io.netty.channel.Channel;

@Component
public class LoginService {

	@Autowired
	private UserService userService;

	public void validateLogin(Channel channel, long userId, String password) {
		User user = userService.validateUser(userId, password);
		IdSession session = ChannelUtils.getSessionBy(channel);
		if (user == null) {
			SessionManager.INSTANCE.sendPacketTo(session,
					ResUserLogin.valueOfFailed());
			return;
		}

		onLoginSucc(user, session);
	}

	private void onLoginSucc(User user, IdSession session) {
		SessionManager.INSTANCE.registerSession(user, session);
		userService.addUser2Online(user);

		ResUserLogin loginPact = new ResUserLogin();
		loginPact.setIsValid(CommonStatus.TRUE);
		SessionManager.INSTANCE.sendPacketTo(session, loginPact);

		SpringContext.getEventDispatcher().fireEvent(
				new UserLoginEvent(EventType.LOGIN, user.getUserId()));
	}

}
