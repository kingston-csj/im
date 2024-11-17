package pers.kinson.im.chat.logic.chat;

import jforgame.commons.DateUtil;
import jforgame.commons.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.dao.DiscussionDao;
import pers.kinson.im.chat.data.dao.DiscussionMemberDao;
import pers.kinson.im.chat.data.dao.MessageDao;
import pers.kinson.im.chat.data.model.Message;
import pers.kinson.im.chat.logic.chat.message.MessageContent;
import pers.kinson.im.chat.logic.chat.message.res.ResNewMessageNotify;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;
import pers.kinson.im.common.constants.Channels;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalChannelHandler implements ChatChannelHandler {

    @Autowired
    MessageDao messageDao;

    @Override
    public void send(Long senderId, Long target, MessageContent content) {
        if (SpringContext.getUserService().getUserName(target) == null) {
            return;
        }
        saveToDb(senderId, target, content);

        ResNewMessageNotify notify = new ResNewMessageNotify();
        notify.setChannel(Channels.person);
        notify.setTopic(target);
        receivers(senderId, target).forEach(e -> SessionManager.INSTANCE.sendPacketTo(e, notify));
    }

    @Override
    public void saveToDb(Long senderId, Long target, MessageContent content) {
        Message message = new Message();
        message.setChannel(channelType());
        message.setDate(new Date());
        message.setSender(senderId);
        message.setReceiver(target);
        message.setContent(JsonUtil.object2String(content));
        messageDao.insert(message);
    }

    @Override
    public Collection<Long> receivers(Long senderId, Long target) {
        return Arrays.asList(senderId, target);
    }

    @Override
    public List<ChatMessage> pullMessages(Long receiver, Long target, long maxSeq) {
        List<Message> newMessages = messageDao.fetchNewPersonal(receiver, maxSeq);
        return newMessages.stream().map(e -> {
            ChatMessage vo = new ChatMessage();
            vo.setId(e.getId());
            vo.setType(e.getType());
            vo.setJson(e.getContent());
            vo.setDate(DateUtil.format(e.getDate()));
            vo.setSenderId(e.getSender());
            vo.setReceiverId(e.getReceiver());
            vo.setReceiverName(SpringContext.getUserService().getUserName(e.getReceiver()));
            vo.setSenderName(SpringContext.getUserService().getUserName(e.getSender()));
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public byte channelType() {
        return Channels.person;
    }
}
