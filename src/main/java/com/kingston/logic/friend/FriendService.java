package com.kingston.logic.friend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.base.Constants;
import com.kingston.base.ServerManager;
import com.kingston.data.dao.FriendDao;
import com.kingston.data.model.User;
import com.kingston.data.view.FriendView;
import com.kingston.logic.friend.message.ResFriendListPacket;
import com.kingston.logic.friend.vo.FriendItemVo;
import com.kingston.logic.user.UserService;

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
	}


}
