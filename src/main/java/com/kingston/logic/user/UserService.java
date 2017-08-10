package com.kingston.logic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.data.dao.UserDao;
import com.kingston.data.model.User;
import com.kingston.logic.GlobalConst;
import com.kingston.logic.user.message.ResUserRegisterPacket;
import com.kingston.net.ChannelUtils;
import com.kingston.net.IoSession;

import io.netty.channel.Channel;

@Component
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 注册新账号
	 * @param userId qq号码
	 * @param nickName 昵称
	 */
	public void registerNewAccount(Channel channel, long userId, String nickName) {
		IoSession session = ChannelUtils.getSessionBy(channel);
		User oldUser = userDao.findById(userId);
		ResUserRegisterPacket response = new ResUserRegisterPacket();
		if (oldUser != null) {
			response.setResultCode(GlobalConst.FAILED);
			response.setMessage("账号id已存在");
			session.sendPacket(response);
		}
	}

}
