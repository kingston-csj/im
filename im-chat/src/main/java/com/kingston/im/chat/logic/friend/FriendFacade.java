package com.kingston.im.chat.logic.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.im.chat.base.SpringContext;
import com.kingston.im.chat.data.model.User;
import com.kingston.im.chat.listener.EventType;
import com.kingston.im.chat.listener.annotation.EventHandler;
import com.kingston.im.chat.logic.login.event.UserLoginEvent;

@Component
public class FriendFacade {

	@Autowired
	private FriendService friendService;

	@EventHandler(value = { EventType.LOGIN })
	public void onUserLogin(UserLoginEvent loginEvent) {
		long userId = loginEvent.getUserId();
		User user = SpringContext.getUserService().getOnlineUser(userId);
		friendService.refreshUserFriends(user);
	}

}
