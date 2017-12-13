package com.kingston.im.logic.friend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.im.base.Constants;
import com.kingston.im.base.ServerManager;
import com.kingston.im.data.dao.FriendDao;
import com.kingston.im.data.model.User;
import com.kingston.im.data.view.FriendView;
import com.kingston.im.logic.friend.message.res.ResFriendListPacket;
import com.kingston.im.logic.friend.message.res.ResFriendLoginPacket;
import com.kingston.im.logic.friend.message.res.ResFriendLogoutPacket;
import com.kingston.im.logic.friend.model.FriendItemVo;
import com.kingston.im.logic.user.UserService;

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

	public void refreshUserFriends(User user) {
		List<FriendItemVo> myFriends = listMyFriends(user.getUserId());
		ResFriendListPacket friendsPact = new ResFriendListPacket();
		friendsPact.setFriends(myFriends);

		ServerManager.INSTANCE.sendPacketTo(user, friendsPact);

		onUserLogin(user);
	}

	public void onUserLogin(User user) {
		List<FriendItemVo> myFriends = listMyFriends(user.getUserId());
		ResFriendLoginPacket loginPact = new ResFriendLoginPacket();
		loginPact.setFriendId(user.getUserId());
		for (FriendItemVo friend:myFriends) {
			long friendId = friend.getUserId();
			if (userService.isOnlineUser(friendId)) {
				ServerManager.INSTANCE.sendPacketTo(friendId, loginPact);
			}
		}
	}

	public void onUserLogout(long userId) {
		List<FriendItemVo> myFriends = listMyFriends(userId);
		ResFriendLogoutPacket logoutPact = new ResFriendLogoutPacket();
		logoutPact.setFriendId(userId);
		for (FriendItemVo friend:myFriends) {
			long friendId = friend.getUserId();
			if (userService.isOnlineUser(friendId)) {
				ServerManager.INSTANCE.sendPacketTo(friendId, logoutPact);
			}
		}
	}


}
