package com.kingston.im.logic.chat;

import org.springframework.stereotype.Component;

import com.kingston.im.data.model.User;
import com.kingston.im.logic.chat.IChatInspector;

@Component
public class ChatInspector implements IChatInspector {

	@Override
	public boolean inspect(User user) {
		return false;
	}

}
