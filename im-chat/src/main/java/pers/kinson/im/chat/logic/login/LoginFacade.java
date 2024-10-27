package pers.kinson.im.chat.logic.login;

import io.netty.channel.Channel;
import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.logic.login.message.req.ReqHeartBeat;
import pers.kinson.im.chat.logic.login.message.req.ReqUserLogin;
import pers.kinson.im.chat.logic.search.SearchService;

@Component
@MessageRoute
public class LoginFacade {

	@Autowired
	private SearchService searchService;

	@RequestHandler
	public void action(IdSession session, ReqHeartBeat E) {
	}

	@RequestHandler
	public void action(IdSession session, ReqUserLogin req) {
		Channel channel = (Channel) session.getRawSession();
		LoginService loginMgr = SpringContext.getBean(LoginService.class);
		loginMgr.validateLogin(channel, req.getUserId(), req.getUserPwd());
	}
}
