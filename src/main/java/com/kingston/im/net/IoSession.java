package com.kingston.im.net;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kingston.im.data.model.User;
import com.kingston.im.net.message.AbstractPacket;

import io.netty.channel.Channel;

/**
 * 链接的会话
 * @author kingston
 */
public class IoSession {
	
	private static final Logger logger = LoggerFactory.getLogger(IoSession.class);

	/** 网络连接channel */
	private Channel channel;

	private User user;

	/** ip地址 */
	private String ipAddr;

	private boolean reconnected;
	
	/** 拓展用，保存一些个人数据  */
	private Map<String, Object> attrs = new HashMap<>();

	public IoSession() {

	}

	public IoSession(Channel channel) {
		this.channel = channel;
		this.ipAddr = ChannelUtils.getIp(channel);
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 向客户端发送消息
	 * @param packet
	 */
	public void sendPacket(AbstractPacket packet) {
		if (packet == null) {
			return;
		}
		if (channel != null) {
			channel.writeAndFlush(packet);
		}
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public boolean isReconnected() {
		return reconnected;
	}

	public void setReconnected(boolean reconnected) {
		this.reconnected = reconnected;
	}

	public User getUser() {
		return user;
	}
	
	public boolean isClose() {
		if (channel == null) {
			return true;
		}
		return !channel.isActive() ||
			   !channel.isOpen();
	}
	
	/**
	 * 关闭session 
	 * @param reason {@link SessionCloseReason}
	 */
	public void close(SessionCloseReason reason) {
		try{
			if (this.channel == null) {
				return;
			}
			if (channel.isOpen()) {
				channel.close();
				logger.info("close session[{}], reason is {}", getUser().getUserId(), reason);
			}else{
				logger.info("session[{}] already close, reason is {}", getUser().getUserId(), reason);
			}
		}catch(Exception e){
		}
	}

}
