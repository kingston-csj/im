package com.kingston.im;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kingston.im.base.ServerNode;
import com.kingston.im.http.HttpServer;
import com.kingston.im.net.ChatServer;

public class ServerStartup {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static ConfigurableApplicationContext context;

	private static List<ServerNode> serverNodes = new ArrayList<>();

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

		serverNodes.add(new ChatServer());
		serverNodes.add(new HttpServer());

		for (ServerNode node : serverNodes) {
			node.init();
			node.start();
		}
	}

	public void stop() {
		context.close();
		for (ServerNode node : serverNodes) {
			try {
				node.shutDown();
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

}
