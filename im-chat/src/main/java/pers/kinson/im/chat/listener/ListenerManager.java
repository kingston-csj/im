package pers.kinson.im.chat.listener;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import pers.kinson.im.common.logger.LoggerUtil;


@Component
public class ListenerManager {


	private Map<String, Method> signature2Handler = new HashMap<>();


	public void registerEventListener(Object listener, EventType eventType, Method method) {
		signature2Handler.put(getKey(listener, eventType), method);
	}

	private String getKey(Object handler, EventType eventType) {
		return handler.getClass().getName() + "-" + eventType.toString();
	}


	/**
	 * 分发给具体监听器执行
	 * @param handler
	 * @param event
	 */
	public void fireEvent(Object handler,BaseEvent event) {
		try {
			Method method = signature2Handler.get(getKey(handler, event.getEventType()));
			method.invoke(handler, event);
		} catch (Exception e) {
			LoggerUtil.error("", e);
		}
	}

}
