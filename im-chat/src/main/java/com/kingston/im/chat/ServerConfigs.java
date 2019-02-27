package com.kingston.im.chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({ "classpath:server.properties" })
public class ServerConfigs {

	/** 服务器ip */
	@Value("${socket.serverIp}")
	private String socketIp;
	/** 服务器端口 */
	@Value("${socket.port}")
	private int socketPort;
	@Value("${http.port}")
	private int httpPort;

	public String getSocketIp() {
		return socketIp;
	}

	public void setSocketIp(String socketIp) {
		this.socketIp = socketIp;
	}

	public int getSocketPort() {
		return socketPort;
	}

	public void setSocketPort(int socketPort) {
		this.socketPort = socketPort;
	}

	public int getHttpPort() {
		return httpPort;
	}

	public void setHttpPort(int httpPort) {
		this.httpPort = httpPort;
	}

}