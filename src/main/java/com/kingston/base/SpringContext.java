package com.kingston.base;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.kingston.asyncdb.AysncDbService;
import com.kingston.logic.chat.ChatService;
import com.kingston.logic.user.UserService;

public class SpringContext implements ApplicationContextAware {
	/** spring容器上下文 */
	private static ApplicationContext applicationContext = null;
	
	private static UserService userService;
	/** 异步持久化服务 */
	private static AysncDbService aysncDbService;
	
	private static ChatService chatService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContext.applicationContext = applicationContext;
	}
	
	public final static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	
	public final static <T> Collection<T> getBeansOfType(Class<T> clazz) {
		return applicationContext.getBeansOfType(clazz).values();
	}
	
	public final static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	@Resource
	public void setUserService(UserService userService) {
		SpringContext.userService = userService;
	}
	
	public final static UserService getUserService() {
		return userService;
	}
	
	@Resource
	public void setAysncDbService(AysncDbService aysncDbService) {
		SpringContext.aysncDbService = aysncDbService;
	}
	
	public static AysncDbService getAysncDbService() {
		return aysncDbService;
	}
	
	@Resource
	public void setChatService(ChatService chatService) {
		SpringContext.chatService = chatService;
	}
	
	public final static ChatService getChatService() {
		return chatService;
	}
	
}
