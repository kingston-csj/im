package com.kingston;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kingston.logs.LoggerUtils;
import com.kingston.net.transport.ChatServer;
import com.kingston.net.transport.ServerConfigs;

public class ServerStartup {

	private static Logger logger = LoggerFactory.getLogger(ServerStartup.class);

	private static ConfigurableApplicationContext context;

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

	public void start() {
		context = new FileSystemXmlApplicationContext("config/applicationContext.xml");

		startNetServer();
	}

	private void startNetServer() {
		try {
			new ChatServer().bind(ServerConfigs.REMOTE_SERVER_PORT);
		} catch (IOException e) {
			LoggerUtils.error("startNetServer failed", e);
		}
	}

	public void stop() {
		context.close();
	}

}
