package com.kingston.im.logic.util;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingston.im.data.dao.UserDao;

@Component
public class IdService {
	
	@Autowired
	private UserDao userDao;
	
	private static AtomicInteger generator;
	
	public void initSedd(int initSeed) {
		
	}

	public int getNextId() {
		if (generator != null) {
			return generator.getAndIncrement();
		}
		synchronized (this) {
			int initSeed = userDao.getMaxId();
			if (initSeed == 0){
				initSeed = 1000;
			}
			generator = new AtomicInteger(initSeed);
			return generator.incrementAndGet();
		}
	}
	
}
