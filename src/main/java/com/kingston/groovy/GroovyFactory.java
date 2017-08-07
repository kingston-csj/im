package com.kingston.groovy;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;  
  
public class GroovyFactory implements ApplicationContextAware {  
  
    private String directory;  
      
    public String getDirectory() {  
        return directory;  
    }  
  
    public void setDirectory(String directory) {  
        this.directory = directory;  
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
          
        String realDirectory = Thread.currentThread().getContextClassLoader().getResource(directory).getFile();  
        File scriptDir = new File(realDirectory);
        if (!scriptDir.exists()) {
        	return;
        }
        
        Collection<File> files = FileUtils.listFiles(scriptDir, new String[]{"groovy"}, true);
        for (File file : files) {  
            GenericBeanDefinition bd = new GenericBeanDefinition();  
            bd.setBeanClassName("org.springframework.scripting.groovy.GroovyScriptFactory");  
            // 刷新时间  
            bd.setAttribute(refreshCheckDelay, 500);  
            // 语言脚本  
            bd.setAttribute(language, "groovy");  
            // 文件目录  
            String filePath = file.getPath();
            String scriptLocator = filePath.substring(filePath.indexOf(directory));
            System.err.println(scriptLocator);
            bd.getConstructorArgumentValues().addIndexedArgumentValue(0, scriptLocator);  
            // 注册到spring容器  
            beanFactory.registerBeanDefinition(file.getName().replace(".groovy", ""), bd);  
        }  
          
    }  
      
} 