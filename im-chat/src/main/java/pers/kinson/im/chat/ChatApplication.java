package pers.kinson.im.chat;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import pers.kinson.business.entity.User;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.logic.chat.ChatService;
import pers.kinson.im.infrastructure.security.AccountServiceClient;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"pers.kinson.im"})
@EnableDiscoveryClient
public class ChatApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ChatApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        final ChatApplication server = new ChatApplication();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
            }
        });
    }

    public void start() throws Exception {
        SpringContext.getBean(jforgame.socket.share.ServerNode.class).start();

        SpringContext.getBean(ChatService.class).init();

        User test = SpringContext.getBean(AccountServiceClient.class).findById(1001L);
        System.out.println(test);
        test = SpringContext.getBean(AccountServiceClient.class).findById(1001L);
        System.out.println(test);
        test = SpringContext.getBean(AccountServiceClient.class).findById(1001L);
    }

}
