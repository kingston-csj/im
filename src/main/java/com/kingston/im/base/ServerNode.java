package com.kingston.im.base;

/**
 * 对外服务器节点
 * 
 * @author kingston
 *
 */
public interface ServerNode {
	
	/**
	 * 服务初始化
	 */
	void init();
	
	/**
 	 *  服务启动
 	 * @throws Exception
 	 */
 	void start() throws Exception;
 	
 	/**
 	 * 服务关闭
 	 * @throws Exception
 	 */
 	void shutDown() throws Exception;
	

}
