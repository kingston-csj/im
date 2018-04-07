package com.kingston.im.logic.login.event;

import com.kingston.im.listener.EventType;
import com.kingston.im.listener.UserEvent;

public class UserLoginEvent extends UserEvent {

	public UserLoginEvent(EventType evtType, long userId) {
		super(evtType, userId);
	}

}
