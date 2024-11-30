package pers.kinson.im.chat.logic.file;

import jforgame.commons.NumberUtil;
import jforgame.socket.share.IdSession;
import jforgame.socket.share.annotation.MessageRoute;
import jforgame.socket.share.annotation.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.core.HttpResult;
import pers.kinson.im.chat.data.dao.MessageDao;
import pers.kinson.im.chat.data.model.Message;
import pers.kinson.im.chat.logic.chat.ChatService;
import pers.kinson.im.chat.logic.file.message.push.PushBeginTransferFile;
import pers.kinson.im.chat.logic.file.message.req.ReqOnlineTransferFileAnswer;
import pers.kinson.im.chat.logic.file.message.req.ReqOnlineTransferFileApply;
import pers.kinson.im.chat.logic.file.message.req.ReqOnlineTransferFileFinish;

@Component
@MessageRoute
public class OnlineTransferFacade {

    @Autowired
    OnlineTransferService transferService;

    @Autowired
    ChatService chatService;

    @Autowired
    MessageDao messageDao;

    @RequestHandler
    public void reqTransferFileApply(IdSession session, ReqOnlineTransferFileApply req) {
        Long userId = NumberUtil.longValue(session.getId());
        int result = transferService.transferApply(userId, req.getReceiverId(), req);
        if (result > 0) {
            session.send(HttpResult.fail(result));
        }
    }

    @RequestHandler
    public void reqTransferFileAnswer(IdSession session, ReqOnlineTransferFileAnswer req) {
        Long userId = NumberUtil.longValue(session.getId());
        int result = transferService.transferAnswer(userId, req.getMessageId(), req.getStatus());
        if (result > 0) {
            session.send(HttpResult.fail(result));
            return;
        }
        Message message = messageDao.selectById(req.getMessageId());
        chatService.refreshMessage(message);

        // 给发起方一个通知
        PushBeginTransferFile pushBeginTransferFile = new PushBeginTransferFile();
        pushBeginTransferFile.setHost(req.getHost());
        pushBeginTransferFile.setSecretKey(req.getSecretKey());
        pushBeginTransferFile.setRequestId(req.getMessageId().toString());
        pushBeginTransferFile.setFileUrl(req.getFileUrl());
        pushBeginTransferFile.setFileName(req.getFileName());
        SessionManager.INSTANCE.sendPacketTo(message.getSender(), pushBeginTransferFile);
    }

    @RequestHandler
    public void reqTransferFileFinish(IdSession session, ReqOnlineTransferFileFinish req) {
        Long userId = NumberUtil.longValue(session.getId());
        int result = transferService.transferFinish(userId, req.getMessageId());
        if (result > 0) {
            session.send(HttpResult.fail(result));
        }
    }

}
