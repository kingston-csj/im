package com.kingston.base;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationContext;

public class ServerDataPool {
	//缓存所有登录用户的通信上下文环境
	public static Map<String,ChannelHandlerContext> SESSION_CHANNEL_MAP  = new ConcurrentHashMap<String,ChannelHandlerContext>();

	public static   ApplicationContext SPRING_BEAN_FACTORY = null;
	
}
