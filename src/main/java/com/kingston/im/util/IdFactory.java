package com.kingston.im.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 全局id生成器
 * @author kingston
 */
public class IdFactory {
	
	private static AtomicLong generator = new AtomicLong(0);
	
	/**
	 * 生成全局唯一id
	 */
	public static long getNextId() {
		//----------------id格式 -------------------------
		//----------long类型8个字节64个比特位----------------
		// 高32位          | 低32位
		// 系统毫秒数        自增长号
		
		return  (((System.currentTimeMillis()/1000) & 0xFFFFFFFF) << 32)
			   |(generator.getAndIncrement() & 0xFFFFFFFF);
	}

}
