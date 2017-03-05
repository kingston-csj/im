package com.kingston.entry;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kingston.base.ServerDataPool;
import com.kingston.netty.NettyChatServer;
import com.kingston.netty.NettyContants;

public class StartServer {

	public static void main(String[] args)  {
		readSpringContextConfig();
		startNetty();
	
	}
	
	private static void startNetty(){
		try {
			new NettyChatServer().bind(NettyContants.REMOTE_SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readSpringContextConfig(){
		ServerDataPool.SPRING_BEAN_FACTORY = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
