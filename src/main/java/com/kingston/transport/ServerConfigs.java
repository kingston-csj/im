package com.kingston.transport;

public class ServerConfigs {
	
	/**  服务器ip */
	public static String REMOTE_SERVER_IP = "127.0.0.1";
	/**  服务器端口 */
	public static int REMOTE_SERVER_PORT = 8080;
	
	/**  客户端断线重连最大尝试次数 */
	public final static int MAX_RECONNECT_TIMES = 10;
}
