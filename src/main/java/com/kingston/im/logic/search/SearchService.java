package com.kingston.im.logic.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.im.base.SessionManager;
import com.kingston.im.data.dao.SearchDao;
import com.kingston.im.data.view.FriendView;
import com.kingston.im.logic.search.message.res.ResSearchFriends;
import com.kingston.im.logic.search.message.vo.RecommendFriendItem;
import com.kingston.im.net.IoSession;

@Component
public class SearchService {

	@Autowired
	private SearchDao searchDao;

	public void search(IoSession session, String key) {
		List<FriendView> users = searchDao.queryByName(key);

		List<RecommendFriendItem> vos = new ArrayList<>();

		for (FriendView item : users) {
			RecommendFriendItem vo = new RecommendFriendItem();
			vo.setUserId(item.getUserId());
			vo.setNickName(item.getUserName());
			vos.add(vo);
		}

		ResSearchFriends friends = new ResSearchFriends();
		friends.setFriends(vos);
		SessionManager.INSTANCE.sendPacketTo(session, friends);
	}

}
