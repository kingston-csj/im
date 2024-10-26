package pers.kinson.im.chat.base;

import java.util.Collection;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jforgame.codec.MessageCodec;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import pers.kinson.im.chat.ServerConfigs;
import pers.kinson.im.chat.asyncdb.AysncDbService;
import pers.kinson.im.chat.dispatch.MessageDispatcher;
import pers.kinson.im.chat.listener.EventDispatcher;
import pers.kinson.im.chat.logic.chat.ChatService;
import pers.kinson.im.chat.logic.friend.FriendService;
import pers.kinson.im.chat.logic.search.SearchService;
import pers.kinson.im.chat.logic.user.UserService;
import pers.kinson.im.chat.logic.util.IdService;

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

    @Getter
    private static UserService userService;

    @Getter
    private static FriendService friendService;

    @Getter
    private static SearchService searchService;

    /**
     * 异步持久化服务
     */
    @Getter
    private static AysncDbService aysncDbService;

    @Getter
    private static ChatService chatService;

    @Getter
    private static IdService idService;

    @Getter
    private static MessageDispatcher messageDispatcher;

    @Getter
    private static EventDispatcher eventDispatcher;

    private static ServerConfigs serverConfigs;

    @Getter
    private static MessageCodec messageCodec;

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

    @Resource
    public void setUserService(UserService userService) {
        SpringContext.userService = userService;
    }

    @Resource
    public void setFriendService(FriendService friendService) {
        SpringContext.friendService = friendService;
    }

    @Resource
    public void setSearchService(SearchService searchService) {
        SpringContext.searchService = searchService;
    }

    @Resource
    public void setIdService(IdService idService) {
        SpringContext.idService = idService;
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
    public void setMessageDispatcher(MessageDispatcher messageDispatcher) {
        SpringContext.messageDispatcher = messageDispatcher;
    }

    @Resource
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        SpringContext.eventDispatcher = eventDispatcher;
    }


    @Resource
    public void setServerConfigs(ServerConfigs serverConfigs) {
        SpringContext.serverConfigs = serverConfigs;
    }

    public static ServerConfigs getServerConfigs() {
        return SpringContext.serverConfigs;
    }

    @Resource
    public void setMessageCodec(MessageCodec messageCodec) {
        SpringContext.messageCodec = messageCodec;
    }


}
