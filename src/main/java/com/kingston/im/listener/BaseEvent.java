package com.kingston.im.listener;

/**
 * 监听器监听的事件抽象类
 */
public abstract class BaseEvent {
	
	/** 创建时间 */
	private long createTime;
	/** 事件类型 */
	private final EventType eventType;
	
	public BaseEvent(EventType evtType) {
		this.createTime = System.currentTimeMillis();
		this.eventType  = evtType;
	}
	
	public long getCreateTime() {
		return this.createTime;
	}
	
	public EventType getEventType() {
		return this.eventType;
	}
	
	/**
	 * 是否在消息主线程同步执行
	 * @return
	 */
	public boolean isSynchronized() {
		return true;
	}

}
