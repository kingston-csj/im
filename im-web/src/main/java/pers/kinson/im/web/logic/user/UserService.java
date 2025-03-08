package pers.kinson.im.web.logic.user;

import jforgame.commons.ds.ConcurrentHashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kinson.business.entity.User;
import pers.kinson.im.common.HttpResult;
import pers.kinson.im.common.constants.I18nConstants;
import pers.kinson.im.oss.OssService;
import pers.kinson.im.web.data.dao.UserDao;
import pers.kinson.im.web.logic.avatar.AvatarService;

import java.util.Set;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    OssService ossService;

    @Autowired
    AvatarService avatarService;

    /**
     * 在线用户列表
     */
    private Set<Long> onlneUsers = new ConcurrentHashSet<>();

    public void removeFromOnline(long userId) {
        this.onlneUsers.remove(userId);
    }

    public User queryUser(long userId) {
        return userDao.selectById(userId);
    }

    /**
     * 注册新账号
     *
     * @param userId 昵称
     */
    public HttpResult registerNewAccount(byte sex, long userId, String password) {
        User oldUser = userDao.selectById(userId);
        if (oldUser != null) {
            return HttpResult.fail(I18nConstants.USER_ID_EXISTED);
        } else {
            User newUser = createNewUser(userId, sex, String.valueOf(userId), password);
            return HttpResult.ok(String.valueOf(newUser.getUserId()));
        }
    }

    private User createNewUser(long userId, byte sex, String nickName, String password) {
        User newUser = new User();
        newUser.setSex(sex);
        newUser.setUserId(userId);
        newUser.setUserName(nickName);
        newUser.setPassword(password);

        String url = avatarService.listAllAvatar().get(0).getUrl();
        newUser.setAvatar(ossService.fullOssPath(url));

        userDao.insert(newUser);

        return newUser;
    }

    public void saveUser(User user) {
        userDao.updateById(user);
    }
}
