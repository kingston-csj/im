package com.kingston.dao;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.ibatis.session.SqlSession;

public abstract class BaseDao {
	
	private static ConcurrentMap<Class<?>, BaseDao> pool = new ConcurrentHashMap<>();
	
	public BaseDao() {
//		register(this);
	}
	
	private void register(Class<?> clazz, BaseDao dao) {
		pool.putIfAbsent(dao.getClass(), dao);
	}
	
	public abstract <T, Id> T queryBy(Id id);
	
	public abstract <T> boolean update(T t, SqlSession session);
	
	public abstract <T> boolean save(T t);
	
	public abstract <T> boolean delete(T t);

}
