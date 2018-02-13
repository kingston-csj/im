package com.kingston.im;

public class ServerConfigs {

	/**  服务器ip */
	private String socketIp;
	/**  服务器端口 */
	private int socketPort;

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