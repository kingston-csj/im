package com.kingston.logic.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.data.model.User;

@Component
public class ChatService {
	
	@Autowired
	private IChatInspector chatInspector;
	
	
	public void chat(long fromUserId, long toUserId, String message) {
//		User fromUser = 
		System.err.println(chatInspector.inspect(null));
	}
	

}
