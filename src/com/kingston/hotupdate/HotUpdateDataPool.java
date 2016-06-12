package com.kingston.hotupdate;

import java.util.HashMap;
import java.util.Map;

import com.kingston.service.login.LoginManagerProxy;

public class HotUpdateDataPool {
	public static Map<String,Class<?>>  hotUpdateMap = new HashMap<String,Class<?>> ();
	static {
		hotUpdateMap.put("LoginManager", LoginManagerProxy.class);
	}

}
