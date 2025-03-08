package pers.kinson.im.chat;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import jforgame.commons.JsonUtil;
import jforgame.commons.NumberUtil;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import pers.kinson.im.chat.base.SpringContext;
import pers.kinson.im.chat.logic.chat.ChatService;

import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"pers.kinson.im"})
@EnableDiscoveryClient
public class ChatApplication   {

    private static void registerSocketInfo() throws NacosException {
        Environment environment = SpringContext.getBean(Environment.class);

        String nacosServerAddress = environment.getProperty("spring.cloud.nacos.config.server-addr"); // Nacos服务器地址
        String serviceName = "socket"; // 服务名
        int tcpPort = NumberUtil.intValue(environment.getProperty("socket.port")); // TCP端口

        // 设置Nacos的属性
        Properties properties = new Properties();
        properties.put("serverAddr", nacosServerAddress);
        // 设置命名空间
        String namespace =  environment.getProperty("spring.cloud.nacos.config.namespace");
        properties.put("namespace", namespace);

        // 获取NamingService实例
        NamingService namingService = NacosFactory.createNamingService(properties);
        // 创建Instance对象，包含TCP端口
        Instance instance = new Instance();
        instance.setIp("127.0.0.1"); // 实例IP
        instance.setPort(tcpPort); // 实例TCP端口
        instance.setServiceName(serviceName);
        String groupName = "im";

        // 注册服务
        namingService.registerInstance(serviceName, groupName, instance);
        System.out.println("socket节点注册成功-->"+ JsonUtil.object2String(instance));
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        List<Instance> allInstances = namingService.getAllInstances(serviceName, groupName);
//        System.out.println("服务列表：" + allInstances);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ChatApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

        SpringContext.getBean(jforgame.socket.share.ServerNode.class).start();

        SpringContext.getBean(ChatService.class).init();

        registerSocketInfo();
    }

}
