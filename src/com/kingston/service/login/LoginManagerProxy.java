package com.kingston.service.login;

import com.kingston.hotupdate.HotUpdateManager;
import com.kingston.hotupdate.HotUpdateProxy;

public class LoginManagerProxy extends HotUpdateProxy{

	private static LoginManager manager = new LoginManagerImpl();

	@Override
	public String getClassName() {
		return LoginManager.class.getSimpleName();
	}

	@Override
	public void setManager(HotUpdateManager instance) {
		LoginManagerProxy.manager = (LoginManager)instance;
	}
	
	public static LoginManager getManager(){
		return LoginManagerProxy.manager;
	}
	
}
