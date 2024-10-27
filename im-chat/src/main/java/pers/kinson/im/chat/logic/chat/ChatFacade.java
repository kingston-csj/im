package pers.kinson.im.chat.logic.chat;

import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.logic.chat.message.req.ReqChatToGroup;
import pers.kinson.im.chat.logic.chat.message.req.ReqChatToUser;
import pers.kinson.im.chat.logic.search.SearchService;

@Component
@MessageRoute
public class ChatFacade {

	@Autowired
	private SearchService searchService;

	@RequestHandler
	public void reqChatToUser(IdSession session, ReqChatToUser req) {
		SpringContext.getChatService().chat(session, req.getToUserId(), req.getContent());
	}

	@RequestHandler
	public void reqChatToGroup(IdSession session, ReqChatToGroup req) {
		SpringContext.getChatService().chat(session, req.getToUserId(), req.getContent());
	}
}
