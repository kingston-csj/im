package pers.kinson.im.chat;

import com.mysql.cj.admin.ServerController;
import jforgame.socket.share.ServerNode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.config.ServerProperties;
import pers.kinson.im.chat.logic.chat.ChatService;
import pers.kinson.im.chat.logic.script.EmojiScript;
import pers.kinson.im.chat.logic.system.SystemController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
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

        SpringContext.getBean(ChatService.class).init();

        // 首次执行——上传表情包
        SpringContext.getBean(EmojiScript.class).updateEmojiResource();

        logger.info("当前客户端版本号：{}", SpringContext.getBean(ServerProperties.class).getVersion());
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
