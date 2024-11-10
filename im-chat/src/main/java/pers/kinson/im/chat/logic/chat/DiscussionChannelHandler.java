package pers.kinson.im.chat.logic.chat;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jforgame.commons.DateUtil;
import jforgame.commons.JsonUtil;
import jforgame.commons.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.dao.DiscussionDao;
import pers.kinson.im.chat.data.dao.DiscussionMemberDao;
import pers.kinson.im.chat.data.dao.MessageDao;
import pers.kinson.im.chat.data.model.Discussion;
import pers.kinson.im.chat.data.model.DiscussionMember;
import pers.kinson.im.chat.data.model.Message;
import pers.kinson.im.chat.logic.chat.message.MessageContent;
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
    public void send(Long senderId, String target, MessageContent content) {
        Long discussionId = NumberUtil.longValue(target);
        Discussion discussion = discussionDao.selectById(discussionId);
        if (discussion == null) {
            return;
        }
    }

    @Override
    public void saveToDb(Long senderId, String target, MessageContent content) {
        Long discussionId = NumberUtil.longValue(target);
        Discussion discussion = discussionDao.selectById(discussionId);
        Message message = new Message();
        message.setChannel(channelType());
        message.setDate(new Date());
        message.setSender(senderId);
        discussion.setMaxSeq(discussion.getMaxSeq() + 1);
        message.setSeq(discussion.getMaxSeq());
        message.setContent(JsonUtil.object2String(content));
    }

    @Override
    public Collection<Long> receivers(Long senderId, String target) {
        Long discussionId = NumberUtil.longValue(target);
        Discussion discussion = discussionDao.selectById(discussionId);
        return memberDao.selectList(new LambdaQueryWrapper<DiscussionMember>().eq(DiscussionMember::getId, discussion))
                .stream().map(DiscussionMember::getUserId).collect(Collectors.toList());
    }

    @Override
    public List<ChatMessage> pullMessages(Long receiver, String target, long maxSeq) {
        List<Message> newMessages = messageDao.fetchNew(channelType(), target, maxSeq);
        return newMessages.stream().map(e -> {
            ChatMessage vo = new ChatMessage();
            vo.setContent(e.getContent());
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
