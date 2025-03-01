package pers.kinson.im.chat.logic.friend.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.business.entity.FriendApply;
import pers.kinson.business.entity.Friends;
import pers.kinson.business.entity.User;
import pers.kinson.business.view.FriendView;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.data.dao.FriendApplyDao;
import pers.kinson.im.chat.data.dao.FriendDao;
import pers.kinson.im.chat.logic.friend.message.res.ResFriendList;
import pers.kinson.im.chat.logic.friend.message.vo.FriendItemVo;
import pers.kinson.im.chat.logic.user.UserService;
import pers.kinson.im.common.constants.CommonStatus;
import pers.kinson.im.common.constants.I18nConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FriendService {

	@Autowired
	private FriendDao friendDao;
	@Autowired
	private UserService userService;
	@Autowired
	FriendApplyDao friendApplyDao;

	public List<FriendItemVo> listMyFriends(long userId) {
		List<FriendItemVo> result = new ArrayList<>();
		List<FriendView> friends = friendDao.getMyFriends(userId);
		for (FriendView f:friends) {
			if (f.getUserId() == userId) {
				continue;
			}
			FriendItemVo item = new FriendItemVo();
			item.setGroup(f.getGroupId());
			item.setRemark(f.getRemark());
			item.setSex(f.getSex());
			item.setSignature(f.getSignature());
			item.setUserId(f.getUserId());
			item.setUserName(f.getUserName());
			item.setGroupName(f.getGroupName());
			item.setHeadUrl(f.getAvatar());
			if (userService.isOnlineUser(f.getUserId())) {
				item.setOnline(CommonStatus.ONLINE_STATUS);
			}

			result.add(item);
		}

		return result;
	}

	public int addNewFriend(Long applyId, Long fromId, Long toId) {
		FriendApply existed = friendApplyDao.selectOne(new LambdaQueryWrapper<FriendApply>().eq(FriendApply::getFromId, fromId)
				.eq(FriendApply::getId, applyId));
		if (existed == null) {
			return I18nConstants.COMMON_NOT_FOUND;
		}
		Friends relation1 = Friends.builder().userId(fromId).friendId(toId).date(new Date()).build();
		Friends relation2 = Friends.builder().userId(toId).friendId(fromId).date(new Date()).build();
		friendDao.insert(relation1);
		friendDao.insert(relation2);
		return 0;
	}

	public boolean isMyFriend(Long userId, Long targetId) {
		Friends existed = friendDao.selectOne(new LambdaQueryWrapper<Friends>().eq(Friends::getUserId, userId)
				.eq(Friends::getFriendId, targetId));
		return existed != null;
	}

	public void refreshUserFriends(User user) {
		List<FriendItemVo> myFriends = listMyFriends(user.getUserId());
		ResFriendList friendsPact = new ResFriendList();
		friendsPact.setFriends(myFriends);

		SessionManager.INSTANCE.sendPacketTo(user, friendsPact);
	}

}
