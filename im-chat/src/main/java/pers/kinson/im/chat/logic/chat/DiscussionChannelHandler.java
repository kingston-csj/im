package pers.kinson.im.chat.logic.chat;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jforgame.commons.DateUtil;
import jforgame.commons.JsonUtil;
import jforgame.commons.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.dao.DiscussionDao;
import pers.kinson.im.chat.data.dao.DiscussionMemberDao;
import pers.kinson.im.chat.data.dao.MessageDao;
import pers.kinson.im.chat.data.model.Discussion;
import pers.kinson.im.chat.data.model.DiscussionMember;
import pers.kinson.im.chat.data.model.Message;
import pers.kinson.im.chat.logic.chat.message.MessageContent;
import pers.kinson.im.chat.logic.chat.message.res.ResNewMessageNotify;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;
import pers.kinson.im.common.constants.Channels;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscussionChannelHandler implements ChatChannelHandler {

    @Autowired
    DiscussionDao discussionDao;
    @Autowired
    DiscussionMemberDao memberDao;

    @Autowired
    MessageDao messageDao;


    @Override
    public void send(Long senderId, Long target, MessageContent content) {
        Long discussionId = NumberUtil.longValue(target);
        Discussion discussion = discussionDao.selectById(discussionId);
        if (discussion == null) {
            return;
        }
        saveToDb(senderId, target, content);

        ResNewMessageNotify notify = new ResNewMessageNotify();
        notify.setChannel(Channels.discussion);
        notify.setTopic(discussionId);
        receivers(senderId, target).forEach(e -> SessionManager.INSTANCE.sendPacketTo(e, notify));
    }

    @Override
    public void saveToDb(Long senderId, Long target, MessageContent content) {
        Long discussionId = NumberUtil.longValue(target);
        Discussion discussion = discussionDao.selectById(discussionId);
        Message message = new Message();
        message.setChannel(channelType());
        message.setDate(new Date());
        message.setSender(senderId);
        message.setReceiver(target);
        long currId = NumberUtil.longValue(discussion.getMaxSeq());
        discussion.setMaxSeq(currId + 1);
        discussionDao.updateById(discussion);
        message.setSeq(discussion.getMaxSeq());
        message.setContent(JsonUtil.object2String(content));
        messageDao.insert(message);
    }

    @Override
    public Collection<Long> receivers(Long senderId, Long target) {
        Long discussionId = NumberUtil.longValue(target);
        Discussion discussion = discussionDao.selectById(discussionId);
        return memberDao.selectList(new LambdaQueryWrapper<DiscussionMember>().eq(DiscussionMember::getDiscussionId, discussionId))
                .stream().map(DiscussionMember::getUserId).collect(Collectors.toList());
    }

    @Override
    public List<ChatMessage> pullMessages(Long receiver, Long target, long maxSeq) {
        List<Message> newMessages = messageDao.fetchNew(channelType(), target, maxSeq);
        return newMessages.stream().map(e -> {
            ChatMessage vo = new ChatMessage();
            vo.setSeq(e.getSeq());
            vo.setType(e.getType());
            vo.setJson(e.getContent());
            vo.setDate(DateUtil.format(e.getDate()));
            vo.setUserId(e.getSender());
            vo.setUserName(SpringContext.getUserService().getUserName(e.getSender()));
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public byte channelType() {
        return Channels.discussion;
    }
}
