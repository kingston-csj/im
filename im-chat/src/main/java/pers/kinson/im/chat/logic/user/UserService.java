package pers.kinson.im.chat.logic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.kinson.business.entity.User;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.data.dao.UserDao;
import pers.kinson.im.chat.logic.user.message.ResUserInfo;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User queryUser(long userId) {
        return userDao.selectById(userId);
    }

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

    public void refreshUserProfile(User user) {
        ResUserInfo response = new ResUserInfo();
        response.setSex(user.getSex());
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setSignature(user.getSignature());
        response.setAvatar(user.getAvatar());
        response.setMaxChatSeq(user.getChatMaxSeq());

        SessionManager.INSTANCE.sendPacketTo(user.getUserId(), response);
    }
}
