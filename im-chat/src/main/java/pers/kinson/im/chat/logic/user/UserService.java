package pers.kinson.im.chat.logic.user;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pers.kinson.im.chat.base.Constants;
import pers.kinson.im.chat.base.SessionManager;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.data.dao.UserDao;
import pers.kinson.im.chat.data.model.User;
import pers.kinson.im.chat.logic.user.message.res.ResUserInfo;
import pers.kinson.im.chat.logic.user.message.res.ResUserRegister;
import pers.kinson.im.chat.logic.util.IdService;
import pers.kinson.im.chat.net.ChannelUtils;
import pers.kinson.im.chat.net.IoSession;
import pers.kinson.im.chat.net.SessionCloseReason;
import pers.kinson.im.common.utils.ConcurrentHashSet;
import pers.kinson.im.common.utils.LruHashMap;

import io.netty.channel.Channel;

@Component
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private IdService idService;

	/** lru缓存最近登录的所有用户 */
	private Map<Long, User> lruUsers = new LruHashMap<>(1000);
	/** 在线用户列表　*/
	private Set<Long> onlneUsers = new ConcurrentHashSet<>();

	public void addUser2Online(User user) {
		this.onlneUsers.add(user.getUserId());
		this.lruUsers.put(user.getUserId(), user);
	}

	public void removeFromOnline(long userId) {
		this.onlneUsers.remove(userId);
	}

	public boolean isOnlineUser(long userId) {
		return this.onlneUsers.contains(userId);
	}

	public User getOnlineUser(long userId) {
		return this.lruUsers.get(userId);
	}

	public User queryUser(long userId, String password) {
		if (userId <= 0 || StringUtils.isEmpty(password)) {
			return null;
		}
		User user = userDao.selectById(userId);
		if (user != null &&
			user.getPassword().equals(password)) {
			return user;
		}

		return null;
	}

	/**
	 * 注册新账号
	 * @param userId 昵称
	 */
	public void registerNewAccount(Channel channel, byte sex, long userId, String password) {
		IoSession session = ChannelUtils.getSessionBy(channel);
		User oldUser = userDao.selectById(userId);
		ResUserRegister response = new ResUserRegister();
		if (oldUser != null) {
			response.setResultCode(Constants.FAILED);
			response.setMessage("账号id已存在");
		}else {
			User newUser = createNewUser(userId, sex, String.valueOf(userId), password);
			response.setResultCode(Constants.SUCC);
			response.setMessage(String.valueOf(newUser.getUserId()));
		}
		session.sendPacket(response);
	}

	private User createNewUser(long userId, byte sex, String nickName, String password) {
		User newUser = new User();
		newUser.setSex(sex);
		newUser.setUserId(userId);
		newUser.setUserName(nickName);
		newUser.setPassword(password);

		userDao.insert(newUser);

		return newUser;
	}

	public void refreshUserProfile(User user) {
		ResUserInfo response = new ResUserInfo();
		response.setSex(user.getSex());
		response.setUserId(user.getUserId());
		response.setUserName(user.getUserName());
		response.setSignature(user.getSignature());

		SessionManager.INSTANCE.sendPacketTo(user.getUserId(), response);
	}

	public void userLogout(Channel channel, SessionCloseReason reason) {
		IoSession session = ChannelUtils.getSessionBy(channel);
		long userId = session.getUser().getUserId();
		SpringContext.getUserService().removeFromOnline(userId);
		SpringContext.getFriendService().onUserLogout(userId);

		SessionManager.INSTANCE.ungisterUserContext(channel, reason);
	}


}
