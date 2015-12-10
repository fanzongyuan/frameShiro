package com.frame.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取spring容器，以访问容器中定义的其他bean  
 * @author FANZONGYUAN
 *
 */
public class SpringFactory implements ApplicationContextAware {

	private static ApplicationContext   applicationContext;  

    /**  
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */ 

    public void setApplicationContext(ApplicationContext applicationContext){  
    	SpringFactory.applicationContext = applicationContext;  
    }  

    public static ApplicationContext getApplicationContext(){  
        return applicationContext;  
    }  


    /**  
     * 获取对象  
     * @param name name
     * @return Object
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException{  
        return applicationContext.getBean(name);
    }  
}