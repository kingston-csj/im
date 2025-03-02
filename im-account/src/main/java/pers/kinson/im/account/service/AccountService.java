package pers.kinson.im.account.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pers.kinson.business.entity.User;
import pers.kinson.im.account.data.dao.UserDao;

@Service
public class AccountService {

    @Autowired
    UserDao userDao;

    public User findAccountByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUserName, username);
        return userDao.selectOne(wrapper);
    }

    @Cacheable(value = "account", key = "#userId")
    public User queryUser(long userId) {
        return userDao.selectById(userId);
    }

    @CachePut(value = "account", key="#user.userId")
    public void saveUser(User user) {
        userDao.updateById(user);
    }

    public boolean isOnlineUser(long userId) {
        return true;
    }

    public String getUserName(long userId) {
        User user = queryUser(userId);
        return user == null ? null : user.getUserName();
    }

}
