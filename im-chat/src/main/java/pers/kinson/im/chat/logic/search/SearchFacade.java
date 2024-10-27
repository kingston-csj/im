package pers.kinson.im.chat.logic.search;

import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.logic.search.message.req.ReqSearchFriends;
import pers.kinson.im.chat.logic.search.message.res.ResSearchFriends;
import pers.kinson.im.chat.logic.search.message.vo.RecommendFriendItem;

import java.util.List;

@Component
@MessageRoute
public class SearchFacade {

    @Autowired
    private SearchService searchService;

    @RequestHandler
    public ResSearchFriends reqSearchFriends(IdSession session, int index,  ReqSearchFriends req) {
        List<RecommendFriendItem> friends = SpringContext.getSearchService().search(req.getKey());
        ResSearchFriends response = new ResSearchFriends();
        response.setFriends(friends);
        return response;
    }
}
