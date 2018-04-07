package com.kingston.im.listener;

/**
 * 用户事件抽象类
 */
public abstract class UserEvent extends BaseEvent {

	/** 用户id */
	private final long userId;

	public UserEvent(EventType evtType, long userId) {
		super(evtType);
		this.userId = userId;
	}

	public long getUserId() {
		return this.userId;
	}
}
