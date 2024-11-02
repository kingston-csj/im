package pers.kinson.im.chat.logic.chat;

import jforgame.commons.NumberUtil;
import jforgame.socket.share.IdSession;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.logic.chat.message.res.ResChatToUser;

@Component
public class ChatService {

    public void chat(IdSession fromUser, long toUserId, String content) {
        IdSession toUser = SessionManager.INSTANCE.getSessionBy(toUserId);
        if (fromUser == null || toUser == null) {
            return;
        }
        if (!checkDirtyWords(content)) {
            return;
        }

        //双方都推送消息
        ResChatToUser response = new ResChatToUser();
        response.setContent(content);
        response.setFromUserId(NumberUtil.longValue(fromUser.getId()));
        response.setToUserId(toUserId);
        toUser.send(response);

        fromUser.send(response);
    }

    private boolean checkDirtyWords(String content) {
        return true;
    }

}
