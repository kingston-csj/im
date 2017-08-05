package com.kingston;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kingston.base.ServerDataPool;
import com.kingston.transport.ChatServer;
import com.kingston.transport.ServerConfigs;

public class ServerStartup {

	public static void main(String[] args)  {
		readSpringContextConfig();
		startNetty();
	
	}
	
	private static void startNetty(){
		try {
			new ChatServer().bind(ServerConfigs.REMOTE_SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readSpringContextConfig(){
		ServerDataPool.SPRING_BEAN_FACTORY = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
