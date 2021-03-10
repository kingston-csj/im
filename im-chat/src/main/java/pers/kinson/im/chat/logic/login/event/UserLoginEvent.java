package pers.kinson.im.chat.logic.login.event;

import pers.kinson.im.chat.listener.EventType;
import pers.kinson.im.chat.listener.UserEvent;

public class UserLoginEvent extends UserEvent {

	public UserLoginEvent(EventType evtType, long userId) {
		super(evtType, userId);
	}

}
