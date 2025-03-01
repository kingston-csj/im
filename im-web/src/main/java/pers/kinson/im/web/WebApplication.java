package pers.kinson.im.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pers.kinson.im.web.logic.script.AvatarScript;
import pers.kinson.im.web.logic.script.EmojiScript;

@SpringBootApplication
@ComponentScan({"pers.kinson.im.web","pers.kinson.im.oss"})
@EnableCaching
@Slf4j
public class WebApplication {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(WebApplication.class, args);
//        // 首次执行——上传表情包
//        context.getBean(EmojiScript.class).uploadResource();
//        // 首次执行——上传头像包
//        context.getBean(AvatarScript.class).uploadResource();
        log.info("(◠‿◠) web节点启动成功");
    }

}