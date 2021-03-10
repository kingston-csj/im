package pers.kinson.im.chat.logs;

import org.slf4j.Logger;

public class LoggerUtils {

	/**
	 * Log an exception at the ERROR level with an
	 * accompanying message.
	 *
	 * @param errMsg the message accompanying the exception
	 * @param e   the exception to log
	 */
	public static void error(String errMsg, Exception e) {
		Logger logger = LoggerSystem.EXCEPTION.getLogger(); 
		logger.error("", e);  
	}
	
	public static void error(String format, Object... arguments) {
		Logger logger = LoggerSystem.EXCEPTION.getLogger(); 
		logger.error(format, arguments);  
	}
}
