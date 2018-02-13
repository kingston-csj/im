package com.kingston.im.dispatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kingston.im.net.IoSession;
import com.kingston.im.net.message.AbstractPacket;
import com.kingston.im.net.message.PacketManager;

public class MessageTask extends DispatchTask {

	private static Logger logger = LoggerFactory.getLogger(MessageTask.class);

	private long userId;
	private IoSession session;
	private AbstractPacket message;

	public static MessageTask valueOf(int distributeKey,
				IoSession session, AbstractPacket message) {
		MessageTask msgTask = new MessageTask();
		msgTask.dispatchKey = distributeKey;
		msgTask.session = session;
		msgTask.message  = message;

		return msgTask;
	}

	public long getUserId() {
		return userId;
	}

	@Override
	public void run() {
		try {
			PacketManager.INSTANCE.execPacket(session, message);
		}catch(Exception e) {
			logger.error("业务处理出现异常", e);
		}
	}

}
