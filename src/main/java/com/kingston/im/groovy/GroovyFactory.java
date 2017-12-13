package com.kingston.im.groovy;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.kingston.im.logic.chat.IChatInspector;  

public class GroovyFactory implements ApplicationContextAware {  

	private Logger logger = LoggerFactory.getLogger(GroovyFactory.class);

	private String scriptRoot;  

	/** 刷新间隔 */
	private int refreshCheckDelay;

	public String getScriptRoot() {
		return scriptRoot;
	}

	public void setScriptRoot(String scriptRoot) {
		this.scriptRoot = scriptRoot;
	}

	public int getRefreshCheckDelay() {
		return refreshCheckDelay;
	}

	public void setRefreshCheckDelay(int refreshCheckDelay) {
		this.refreshCheckDelay = refreshCheckDelay;
	}

	@Override  
	public void setApplicationContext(ApplicationContext context)  
			throws BeansException {  
		// 只有这个对象才能注册bean到spring容器  
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();  

		// 因为spring会自动将xml解析成BeanDefinition对象然后进行实例化，这里我们没有用xml，所以自己定义BeanDefinition  
		// 这些信息跟spring配置文件的方式差不多，只不过有些东西lang:groovy标签帮我们完成了  
		final String refreshCheckDelay = "org.springframework.scripting.support.ScriptFactoryPostProcessor.refreshCheckDelay";  
		final String language = "org.springframework.scripting.support.ScriptFactoryPostProcessor.language";  

		String realDirectory = new File("").getAbsolutePath() + File.separator+scriptRoot;
		logger.info("加载groovy脚本的目录为:{}", realDirectory);
		File scriptDir = new File(realDirectory);
		if (!scriptDir.exists()) {
			return;
		}
		logger.info("开始加载groovy脚本");
		Collection<File> files = FileUtils.listFiles(scriptDir, new String[]{"java"}, true);
		for (File file : files) {  
			GenericBeanDefinition bd = new GenericBeanDefinition();  
			bd.setBeanClassName("org.springframework.scripting.groovy.GroovyScriptFactory");  
			// 刷新时间  
			bd.setAttribute(refreshCheckDelay, 500);  
			// 语言脚本  
			bd.setAttribute(language, "groovy");  
			// 文件目录  
			String filePath = file.getPath();
			String scriptLocator = filePath.substring(filePath.indexOf(scriptRoot));
			String beanName = file.getName().replace(".java", "");
			logger.info("注册groovy bean [{}] ", scriptLocator);
			bd.getConstructorArgumentValues().addIndexedArgumentValue(0, scriptLocator);  
			// 注册到spring容器  
			beanFactory.registerBeanDefinition(beanName, bd);  

		}  

	}  

} 