package com.kingston.hotupdate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class HotUpdateManagerImpl {

	DynamicClassLoader dc =null; 

	Long lastModified = 0l; 
	Class c = null; 
	//加载类， 如果类文件修改过加载，如果没有修改，返回当前的 
	public Class loadClass(String name) throws ClassNotFoundException, IOException{ 
		if (isClassModified(name)){ 
			dc =  new DynamicClassLoader(); 
			return c = dc.findClass(getBytes(name)); 
		} 
		return c; 
	} 
	
	//判断是否被修改过 
	private boolean isClassModified(String filename) { 
		boolean returnValue = false; 
		File file = new File(filename); 
		if (file.lastModified() > lastModified) { 
			returnValue = true; 
		} 
		return returnValue; 
	} 
	
	// 从本地读取文件 
	private byte[] getBytes(String filename) throws IOException { 
		File file = new File(filename); 
		long len = file.length(); 
		lastModified = file.lastModified(); 
		byte raw[] = new byte[(int) len]; 
		FileInputStream fin = new FileInputStream(file); 
		try{
			int r = fin.read(raw); 
			if (r  != len) { 
				throw new IOException("Can't read all, " + r + " != " + len); 
			} 
		}finally{
			fin.close(); 
		}
		return raw; 
	} 
}
