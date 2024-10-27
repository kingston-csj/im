package pers.kinson.im.chat.logic.friend;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.base.Constants;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.data.dao.FriendDao;
import pers.kinson.im.chat.data.model.Friends;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.data.view.FriendView;
import pers.kinson.im.chat.logic.friend.message.res.ResFriendList;
import pers.kinson.im.chat.logic.friend.message.res.ResFriendLogin;
import pers.kinson.im.chat.logic.friend.message.res.ResFriendLogout;
import pers.kinson.im.chat.logic.friend.message.vo.FriendItemVo;
import pers.kinson.im.chat.logic.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class FriendService {

	@Autowired
	private FriendDao friendDao;
	@Autowired
	private UserService userService;


	public List<FriendItemVo> listMyFriends(long userId) {
		List<FriendItemVo> result = new ArrayList<>();
		List<FriendView> friends = friendDao.getMyFriends(userId);
		for (FriendView f:friends) {
			if (f.getUserId() == userId) {
				continue;
			}
			FriendItemVo item = new FriendItemVo();
			item.setGroup(f.getGroup());
			item.setRemark(f.getRemark());
			item.setSex(f.getSex());
			item.setSignature(f.getSignature());
			item.setUserId(f.getUserId());
			item.setUserName(f.getUserName());
			item.setGroupName(f.getGroupName());
			if (userService.isOnlineUser(f.getUserId())) {
				item.setOnline(Constants.ONLINE_STATUS);
			}

			result.add(item);
		}

		return result;
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

		onUserLogin(user);
	}

	public void onUserLogin(User user) {
		List<FriendItemVo> myFriends = listMyFriends(user.getUserId());
		ResFriendLogin loginPact = new ResFriendLogin();
		loginPact.setFriendId(user.getUserId());
		for (FriendItemVo friend:myFriends) {
			long friendId = friend.getUserId();
			if (userService.isOnlineUser(friendId)) {
				SessionManager.INSTANCE.sendPacketTo(friendId, loginPact);
			}
		}
	}

	public void onUserLogout(long userId) {
		List<FriendItemVo> myFriends = listMyFriends(userId);
		ResFriendLogout logoutPact = new ResFriendLogout();
		logoutPact.setFriendId(userId);
		for (FriendItemVo friend:myFriends) {
			long friendId = friend.getUserId();
			if (userService.isOnlineUser(friendId)) {
				SessionManager.INSTANCE.sendPacketTo(friendId, logoutPact);
			}
		}
	}


}
