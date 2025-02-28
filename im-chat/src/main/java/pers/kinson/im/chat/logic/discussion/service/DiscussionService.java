package pers.kinson.im.chat.logic.discussion.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jforgame.commons.TimeUtil;
import jforgame.commons.ds.LazyCacheMap;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.kinson.business.entity.Discussion;
import pers.kinson.business.entity.DiscussionMember;
import pers.kinson.business.entity.User;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.dao.DiscussionDao;
import pers.kinson.im.chat.data.dao.DiscussionMemberDao;
import pers.kinson.im.chat.logic.chat.message.vo.ChatMessage;
import pers.kinson.im.chat.logic.discussion.message.vo.DiscussionGroupVo;
import pers.kinson.im.chat.logic.discussion.message.vo.DiscussionMemberVo;
import pers.kinson.im.chat.mapstruct.DiscussionMapper;
import pers.kinson.im.chat.mapstruct.DiscussionMemberMapper;
import pers.kinson.im.common.constants.CommonStatus;
import pers.kinson.im.common.constants.I18nConstants;
import pers.kinson.im.infrastructure.security.AccountServiceClient;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
public class DiscussionService {

    @Autowired
    DiscussionDao discussionDao;
    @Autowired
    DiscussionMemberDao memberDao;
    @Autowired
    AccountServiceClient accountServiceClient;

    ConcurrentMap<Long, LazyCacheMap<Long, ChatMessage>> groupMessages = new ConcurrentHashMap<>();

    public int create(Long ownerId, List<Long> members, String name) {
        User user = SpringContext.getUserService().queryUser(ownerId);
        Discussion discussion = new Discussion();
        discussion.setCreatorId(ownerId);
        discussion.setName(name);
        Date now = new Date();
        discussion.setCreatedDate(now);
        // 先临时按创建者的头像吧
        discussion.setAvatar(SpringContext.getUserService().queryUser(ownerId).getAvatar());
        discussionDao.insert(discussion);

        //自动创建一条成员记录
        newMember(ownerId, discussion.getId(), now);
        newMember(ownerId, discussion.getId(), now);
        if (!CollectionUtils.isEmpty(members)) {
            members.forEach(e -> newMember(e, discussion.getId(), now));
        }

        groupMessages.put(discussion.getId(), new LazyCacheMap<>(1000, TimeUtil.MILLIS_PER_HOUR));
        return 0;
    }

    private @NotNull DiscussionMember newMember(Long ownerId, Long discussionId, Date now) {
        DiscussionMember member = new DiscussionMember();
        member.setDiscussionId(discussionId);
        member.setUserId(ownerId);
        member.setJoinDate(now);
        member.setNickName(accountServiceClient.queryUserProfile(ownerId).getName());
        memberDao.insert(member);
        return member;
    }

    public int join(Long userId, Long discussionId) {
        User user = SpringContext.getUserService().queryUser(userId);
        if (user == null) {
            return I18nConstants.COMMON_NOT_FOUND;
        }
        Discussion discussion = discussionDao.selectById(discussionId);
        if (discussion == null) {
            return I18nConstants.COMMON_NOT_FOUND;
        }
        DiscussionMember existed = memberDao.selectOne(new LambdaQueryWrapper<DiscussionMember>().eq(DiscussionMember::getDiscussionId, discussionId)
                .eq(DiscussionMember::getUserId, userId));
        if (existed != null) {
            return I18nConstants.COMMON_ILLEGAL_PARAMS;
        }
        DiscussionMember newMember = new DiscussionMember();
        newMember.setUserId(userId);
        newMember.setDiscussionId(discussionId);
        newMember.setJoinDate(new Date());
        newMember.setNickName(user.getUserName());
        memberDao.insert(newMember);
        return 0;
    }

    public int leave(Long userId, Long discussionId) {
        User user = SpringContext.getUserService().queryUser(userId);
        if (user == null) {
            return I18nConstants.COMMON_NOT_FOUND;
        }
        Discussion discussion = discussionDao.selectById(discussionId);
        if (discussion == null) {
            return I18nConstants.COMMON_NOT_FOUND;
        }
        DiscussionMember existed = memberDao.selectOne(new LambdaQueryWrapper<DiscussionMember>().eq(DiscussionMember::getDiscussionId, discussionId)
                .eq(DiscussionMember::getUserId, userId));
        if (existed == null) {
            return I18nConstants.COMMON_ILLEGAL_PARAMS;
        }
        memberDao.deleteById(existed.getId());
        return 0;
    }

    public List<DiscussionGroupVo> listAllDiscussion(Long userId) {
        return discussionDao.getMyGroups(userId).stream().map(DiscussionMapper.INSTANCE::toVo).toList();
    }

    public List<DiscussionMemberVo> listGroupMembers(Long discussionId) {
        List<DiscussionMember> members = memberDao.selectList(new LambdaQueryWrapper<DiscussionMember>().eq(DiscussionMember::getDiscussionId, discussionId));
        List<DiscussionMemberVo> memberVos = members.stream().map(DiscussionMemberMapper.INSTANCE::toVo).collect(Collectors.toList());
        for (DiscussionMemberVo memberVo : memberVos) {
            if (accountServiceClient.queryUserProfile(memberVo.getUserId()).getOnlineStatus() > 0) {
                memberVo.setOnline(CommonStatus.ONLINE_STATUS);
            }
            memberVo.setAvatar(SpringContext.getUserService().queryUser(memberVo.getUserId()).getAvatar());
        }
        return memberVos;

    }
}
