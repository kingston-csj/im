package com.kingston.logic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.data.dao.UserDao;
import com.kingston.data.model.User;
import com.kingston.logic.GlobalConst;
import com.kingston.logic.user.message.ResUserRegisterPacket;
import com.kingston.logic.util.IdService;
import com.kingston.net.ChannelUtils;
import com.kingston.net.IoSession;

import io.netty.channel.Channel;

@Component
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * 注册新账号
	 * @param userId qq号码
	 * @param nickName 昵称
	 */
	public void registerNewAccount(Channel channel, byte sex, String nickName, String password) {
		IoSession session = ChannelUtils.getSessionBy(channel);
		User oldUser = userDao.findByName(nickName);
		ResUserRegisterPacket response = new ResUserRegisterPacket();
		if (oldUser != null) {
			response.setResultCode(GlobalConst.FAILED);
			response.setMessage("账号id已存在");
		}else {
			User newUser = createNewUser(sex, nickName, password);
			response.setResultCode(GlobalConst.SUCC);
			response.setMessage(String.valueOf(newUser.getUserId()));
		}
		session.sendPacket(response);
	}
	
	private User createNewUser(byte sex, String nickName, String password) {
		int newId = idService.getNextId();
		User newUser = new User();
		newUser.setSex(sex);
		newUser.setUserId(newId);
		newUser.setUserName(nickName);
		newUser.setPassword(password);
		
		userDao.addUser(newUser);
		
		return newUser;
	}

}
