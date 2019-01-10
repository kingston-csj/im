package com.kingston.im.chat.dispatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.kingston.im.common.thread.NamedThreadFactory;


@Component
public class MessageDispatcher {

	private final int CORE_SIZE = Runtime.getRuntime().availableProcessors();

	private ExecutorService[] executors = new ExecutorService[CORE_SIZE];

	@PostConstruct
	public void init() {
		for (int i=0; i<executors.length; i++) {
			ExecutorService executor = Executors.newSingleThreadExecutor(
					new NamedThreadFactory("message-dispatcher"));
			 executors[i] = executor;
		}
	}

	public void addMessageTask(DispatchTask task) {
		// 根据分发id求模映射
		int index = task.getDispatchKey() % executors.length;
		executors[index].submit(task);
	}

}
