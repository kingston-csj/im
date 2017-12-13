package com.kingston.im.util;

import java.util.Map;

public class StringUtil {

	public static boolean isEmpty(String content){
		return content == null || content.length() == 0;
	}
	
	public static boolean isEmpty(Map map){
		return map == null || map.size() == 0;
	}
}
