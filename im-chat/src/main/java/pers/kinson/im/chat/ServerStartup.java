package pers.kinson.im.chat;

import jforgame.socket.share.ServerNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pers.kinson.im.chat.base.SpringContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan({"pers.kinson.im"})
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
        SpringContext.getBean(jforgame.socket.share.ServerNode.class).start();
    }

    public void stop() {
        for (ServerNode node : serverNodes) {
            try {
                node.shutdown();
            } catch (Exception e) {
                logger.error("", e);
            }
        }
    }

}
