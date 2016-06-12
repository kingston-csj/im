package com.kingston.service.login;

import io.netty.channel.ChannelHandlerContext;

public interface LoginManager {

	public void validateLogin(ChannelHandlerContext context,Integer userId,String password);
}
