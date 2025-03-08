package pers.kinson.im.web.logic.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.business.view.FriendView;
import pers.kinson.im.web.data.dao.SearchDao;
import pers.kinson.im.web.logic.search.message.vo.RecommendFriendItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class SearchService {

    @Autowired
    private SearchDao searchDao;

    public List<RecommendFriendItem> search(String key) {
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

        return vos;
    }

}
