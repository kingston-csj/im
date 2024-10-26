package pers.kinson.im.chat.logic.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.data.dao.SearchDao;
import pers.kinson.im.chat.data.view.FriendView;
import pers.kinson.im.chat.logic.search.message.res.ResSearchFriends;
import pers.kinson.im.chat.logic.search.message.vo.RecommendFriendItem;
import pers.kinson.im.chat.net.IoSession;

@Component
public class SearchService {

	@Autowired
	private SearchDao searchDao;

	public void search(IoSession session, String key) {
		List<FriendView> users = new LinkedList<>();
		users.addAll(searchDao.queryByName(key));
		users.addAll(searchDao.queryById(key));

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
