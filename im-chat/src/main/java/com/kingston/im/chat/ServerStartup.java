package com.kingston.im.chat;

import com.kingston.im.chat.base.ServerNode;
import com.kingston.im.chat.http.HttpServer;
import com.kingston.im.chat.net.ChatServer;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan("com.kingston.im.chat.data.dao")
public class ServerStartup implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static List<ServerNode> serverNodes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ServerStartup.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
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
        serverNodes.add(new ChatServer());
        serverNodes.add(new HttpServer());

        for (ServerNode node : serverNodes) {
            node.init();
            node.start();
        }
    }

    public void stop() {
        for (ServerNode node : serverNodes) {
            try {
                node.shutDown();
            } catch (Exception e) {
                logger.error("", e);
            }
        }
    }

}
