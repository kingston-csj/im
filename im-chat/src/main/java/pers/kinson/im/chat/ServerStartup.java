package pers.kinson.im.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.kinson.im.chat.base.ServerNode;
import pers.kinson.im.chat.net.ChatServer;
import pers.kinson.im.chat.net.message.MessageRouter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
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

        for (ServerNode node : serverNodes) {
            node.init();
            node.start();
        }

        MessageRouter.INSTANCE.toString();
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
