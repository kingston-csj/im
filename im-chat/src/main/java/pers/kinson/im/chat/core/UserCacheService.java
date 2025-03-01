package pers.kinson.im.chat.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pers.kinson.business.cache.UserProfile;
import pers.kinson.business.entity.User;
import pers.kinson.im.chat.data.dao.UserDao;

@Service
public class UserCacheService {

    @Autowired
    UserDao userDao;

    @Cacheable(value = "simpleGame", key = "#id")
    public UserProfile get(long id) {
        User user = userDao.selectById(id);
        if (user == null) {
            return null;
        }
        UserProfile profile = new UserProfile();
        profile.setId(user.getUserId());
        profile.setName(user.getUserName());
        return profile;
    }

}
