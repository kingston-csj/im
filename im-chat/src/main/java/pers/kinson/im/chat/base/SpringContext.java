package pers.kinson.im.chat.base;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import pers.kinson.im.chat.ServerConfigs;
import pers.kinson.im.chat.asyncdb.AysncDbService;
import pers.kinson.im.chat.listener.EventDispatcher;
import pers.kinson.im.chat.logic.chat.ChatService;
import pers.kinson.im.chat.logic.chat.MessageContentFactory;
import pers.kinson.im.chat.logic.user.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

@Component
public class SpringContext implements ApplicationContextAware {

    private static SpringContext self;

    /**
     * spring容器上下文
     */
    private static ApplicationContext applicationContext = null;

    @PostConstruct
    private void init() {
        self = this;
    }

    /**
     * 异步持久化服务
     */
    @Getter
    private static AysncDbService aysncDbService;

    @Getter
    private static ChatService chatService;

    @Getter
    private static UserService userService;

    @Getter
    private static EventDispatcher eventDispatcher;

    private static ServerConfigs serverConfigs;

    @Getter
    private static MessageContentFactory messageContentFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> Collection<T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz).values();
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        return applicationContext.getBeansWithAnnotation(annotationType);
    }

    @Resource
    public void setAysncDbService(AysncDbService aysncDbService) {
        SpringContext.aysncDbService = aysncDbService;
    }

    @Resource
    public void setChatService(ChatService chatService) {
        SpringContext.chatService = chatService;
    }

    @Resource
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        SpringContext.eventDispatcher = eventDispatcher;
    }

    @Resource
    public void setServerConfigs(ServerConfigs serverConfigs) {
        SpringContext.serverConfigs = serverConfigs;
    }

    @Resource
    public void setMessageContentFactory(MessageContentFactory messageContentFactory) {
        SpringContext.messageContentFactory = messageContentFactory;
    }

    @Resource
    public void setUserService(UserService userService) {
        SpringContext.userService = userService;
    }
}
