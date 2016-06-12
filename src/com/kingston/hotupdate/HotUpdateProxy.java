package com.kingston.hotupdate;

import java.io.File;
import java.lang.reflect.Method;

public abstract class HotUpdateProxy {
	public abstract String getClassName();
	
//	public abstract static  IHotUpdateManager  getManager();
	
	public abstract void  setManager(HotUpdateManager instance);
	
	public void reload(String clazzName) throws Exception{
		Class proxy = HotUpdateDataPool.hotUpdateMap.get(clazzName);
		Object proxyObj = proxy.newInstance();
		
		try {
			String basePath = new File("").getAbsolutePath()+File.separator+"class"+File.separator+clazzName+".class";
			Class manager = new HotUpdateManagerImpl().loadClass(basePath);
			HotUpdateManager managerObj = (HotUpdateManager)manager.newInstance();
			setManager(managerObj);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
