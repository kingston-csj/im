package com.kingston.im.chat.logic.login.event;

import com.kingston.im.chat.listener.EventType;
import com.kingston.im.chat.listener.UserEvent;

public class UserLoginEvent extends UserEvent {

	public UserLoginEvent(EventType evtType, long userId) {
		super(evtType, userId);
	}

}
