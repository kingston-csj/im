package com.kingston;

public class ServerConfigs {
	
	/**  服务器ip */
	private String socketIp = "127.0.0.1";
	/**  服务器端口 */
	private int socketPort = 8080;
	
	private int httpPort = 10086;

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