package pers.kinson.im.chat.logic.file;

import jforgame.commons.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.kinson.business.entity.Message;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.dao.MessageDao;
import pers.kinson.im.chat.logic.chat.ChatService;
import pers.kinson.im.chat.logic.chat.message.FileOnlineTransferMessageContent;
import pers.kinson.im.chat.logic.file.message.req.ReqOnlineTransferFileApply;
import pers.kinson.im.common.constants.Channels;
import pers.kinson.im.common.constants.ContentType;
import pers.kinson.im.common.constants.I18nConstants;
import pers.kinson.im.common.utils.IdFactory;

import java.util.Objects;

@Service
public class OnlineTransferService {

    @Autowired
    ChatService chatService;

    @Autowired
    MessageDao messageDao;

    public int transferApply(Long fromId, Long toId, ReqOnlineTransferFileApply params) {
        // 目标不在线，无法使用在线传输
//        if (!SpringContext.getUserService().isOnlineUser(toId)) {
//            return I18nConstants.CHAT_TRANSFER_TARGET_OFFLINE;
//        }
        // 系统自动生成一份聊天内容
        FileOnlineTransferMessageContent content = new FileOnlineTransferMessageContent();
        content.setName(params.getFileName());
        content.setFileUrl(params.getFilePath());
        content.setSize(params.getFileSize());
        content.setRequestId(IdFactory.nextUUId());
        content.setFromId(fromId);
        content.setToId(toId);
        content.setType(ContentType.onlineTransfer);
        chatService.chatToChannel(fromId, Channels.person, toId, ContentType.onlineTransfer, JsonUtil.object2String(content));

        return 0;
    }

    public int transferAnswer(Long userId, Long messageId, byte status) {
        Message message = messageDao.selectById(messageId);
        if (message == null) {
            return I18nConstants.COMMON_NOT_FOUND;
        }
        if (!Objects.equals(message.getReceiver(), userId)) {
            return I18nConstants.COMMON_ILLEGAL_PARAMS;
        }
        // 持久化消息内容
        FileOnlineTransferMessageContent messageContent = (FileOnlineTransferMessageContent) SpringContext.getMessageContentFactory().parse(message.getType(), message.getContent());
        messageContent.setStatus(status);
        message.setContent(JsonUtil.object2String(messageContent));
        messageDao.updateById(message);
        return 0;
    }

    public int transferFinish(Long userId, Long messageId) {
        Message message = messageDao.selectById(messageId);
        if (message == null) {
            return I18nConstants.COMMON_NOT_FOUND;
        }
        if (!Objects.equals(message.getSender(), userId)) {
            return I18nConstants.COMMON_ILLEGAL_PARAMS;
        }
        // 持久化消息内容
        FileOnlineTransferMessageContent messageContent = (FileOnlineTransferMessageContent) SpringContext.getMessageContentFactory().parse(message.getType(), message.getContent());
        messageContent.setStatus(FileOnlineTransferMessageContent.STATUS_OK);
        message.setContent(JsonUtil.object2String(messageContent));
        messageDao.updateById(message);

        // 通知状态变更
        chatService.refreshMessage(message);

        return 0;
    }


}
