package pers.kinson.im.chat.logic.search.handler;

import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.dispatch.MessageHandler;
import pers.kinson.im.chat.logic.search.message.req.ReqSearchFriends;
import pers.kinson.im.chat.net.IoSession;

public class SearchFriendHandler extends MessageHandler<ReqSearchFriends> {

    @Override
    public void action(IoSession session, ReqSearchFriends req) {
        SpringContext.getSearchService().search(session, req.getKey());
    }
}
