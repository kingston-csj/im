package com.kingston;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kingston.http.HttpServer;
import com.kingston.net.transport.ChatServer;

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
		context = new FileSystemXmlApplicationContext("config/applicationContext.xml");
		httpServer.start(ServerConfigs.HTTP_PORT);
		new ChatServer().bind(ServerConfigs.REMOTE_SERVER_PORT);
	}


	public void stop() {
		context.close();
	}

}
