package pers.kinson.im.chat.logic.chat.handler;

import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.dispatch.MessageHandler;
import pers.kinson.im.chat.logic.chat.message.req.ReqChatToGroup;
import pers.kinson.im.chat.net.IoSession;

public class ChatToGroupHandler extends MessageHandler<ReqChatToGroup> {

    @Override
    public void action(IoSession session, ReqChatToGroup req) {
        SpringContext.getChatService().chat(session, req.getToUserId(), req.getContent());
    }
}
