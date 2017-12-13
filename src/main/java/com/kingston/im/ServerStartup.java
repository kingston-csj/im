package com.kingston.im;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kingston.im.base.SpringContext;
import com.kingston.im.http.HttpServer;
import com.kingston.im.net.message.PacketType;
import com.kingston.im.net.transport.ChatServer;

public class ServerStartup {

	private static Logger logger = LoggerFactory.getLogger(ServerStartup.class);

	private static ConfigurableApplicationContext context;

	private static HttpServer httpServer = new HttpServer();

	public static void main(String[] args) throws Exception {

		final ServerStartup server = new ServerStartup();
		server.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				server.stop();
			}
		});

	}

	public void start() throws Exception {
		PacketType.initPackets();
		context = new FileSystemXmlApplicationContext("config/applicationContext.xml");
		
		ServerConfigs serverConfigs = SpringContext.getServerConfigs();
		httpServer.start(serverConfigs.getHttpPort());
		new ChatServer().bind(serverConfigs.getSocketPort());
	}


	public void stop() {
		context.close();
	}

}
