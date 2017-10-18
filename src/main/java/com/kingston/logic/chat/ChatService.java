package com.kingston.logic.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.base.ServerManager;
import com.kingston.logic.chat.message.ResChatToUserPacket;
import com.kingston.net.IoSession;

@Component
public class ChatService {
	
	@Autowired
	private IChatInspector chatInspector;
	
	public void chat(long fromUserId, long toUserId, String message) {
		IoSession fromUser = ServerManager.INSTANCE.getSessionBy(fromUserId);
		IoSession toUser = ServerManager.INSTANCE.getSessionBy(toUserId);
		if (fromUser == null || toUser == null) {
			return;
		}
		ResChatToUserPacket response = new ResChatToUserPacket();
		response.setContent(message);
		toUser.sendPacket(response);
	}
	

}
