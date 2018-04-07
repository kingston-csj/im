package com.kingston.im.logic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.im.base.SpringContext;
import com.kingston.im.data.model.User;
import com.kingston.im.listener.EventType;
import com.kingston.im.listener.annotation.EventHandler;
import com.kingston.im.logic.login.event.UserLoginEvent;

@Component
public class UserFacade {

	@Autowired
	private UserService userService;

	@EventHandler(value = { EventType.LOGIN })
	public void onUserLogin(UserLoginEvent loginEvent) {
		long userId = loginEvent.getUserId();
		User user = SpringContext.getUserService().getOnlineUser(userId);
		userService.refreshUserProfile(user);
	}

}
