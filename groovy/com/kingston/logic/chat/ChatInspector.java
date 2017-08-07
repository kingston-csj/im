package com.kingston.logic.chat;

import org.springframework.stereotype.Component;

import com.kingston.data.model.User;

@Component
public class ChatInspector implements IChatInspector {

	@Override
	public boolean inspect(User user) {
		return false;
	}

}
