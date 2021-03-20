package pers.kinson.im.chat.logic.chat.handler;

import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.dispatch.MessageHandler;
import pers.kinson.im.chat.logic.chat.message.req.ReqChatToUser;
import pers.kinson.im.chat.net.IoSession;

public class ChatToUserHandler extends MessageHandler<ReqChatToUser> {
    @Override
    public void action(IoSession session, ReqChatToUser req) {
        SpringContext.getChatService().chat(session, req.getToUserId(), req.getContent());
    }
}
