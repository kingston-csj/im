package com.kingston.base;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kingston.net.Packet;
import com.kingston.util.StringUtil;

public enum ServerManager {
	
	INSTANCE;

	//缓存所有登录用户对应的通信上下文环境（主要用于业务数据处理）
	private  Map<Integer,ChannelHandlerContext> USER_CHANNEL_MAP  = new ConcurrentHashMap<>();
	//缓存通信上下文环境对应的登录用户（主要用于服务）
	private Map<ChannelHandlerContext,Integer> CHANNEL_USER_MAP  = new ConcurrentHashMap<>();
	
	public void sendPacketTo(Packet pact,Integer userId){
		if(pact == null || userId <= 0) return;
		
		Map<Integer,ChannelHandlerContext> contextMap  = USER_CHANNEL_MAP;
		if(StringUtil.isEmpty(contextMap)) return;
		
		ChannelHandlerContext targetContext = contextMap.get(userId);
		if(targetContext == null) return;
		
		targetContext.writeAndFlush(pact);
	}
	
	/**
	 *  向所有在线用户发送数据包
	 */
	public void sendPacketToAllUsers(Packet pact){
		if(pact == null ) return;
		Map<Integer,ChannelHandlerContext> contextMap  = USER_CHANNEL_MAP;
		if(StringUtil.isEmpty(contextMap)) return;
		
		contextMap.values().forEach( (ctx) -> ctx.writeAndFlush(pact));
		
	}
	
	/**
	 *  向单一在线用户发送数据包
	 */
	public void sendPacketTo(Packet pact,ChannelHandlerContext targetContext ){
		if(pact == null || targetContext == null) return;
		targetContext.writeAndFlush(pact);
	}
	
	public ChannelHandlerContext getOnlineContextBy(String userId){
		return USER_CHANNEL_MAP.get(userId);
	}
	
	public Map<Integer,ChannelHandlerContext> getAllOnlineContext(){
		return USER_CHANNEL_MAP;
	}
	
	public void addOnlineContext(Integer userId,ChannelHandlerContext context){
		if(context == null){
			throw new NullPointerException("context is null");
		}
		USER_CHANNEL_MAP.put(userId,context);
		CHANNEL_USER_MAP.put(context, userId);
	}
	
	/**
	 *  注销用户通信渠道
	 */
	public void ungisterUserContext(ChannelHandlerContext context ){
		if(context  != null){
			int userId = CHANNEL_USER_MAP.getOrDefault(context,0);
			CHANNEL_USER_MAP.remove(context);
			USER_CHANNEL_MAP.remove(userId);
			context.close();
		}
	}
	
}
