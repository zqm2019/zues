package com.it.zqm.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @Describe:
 * @Author：zhaqianming
 * @Date: 2021/12/6
 */
@Configuration
public class ApplicationContextConfig implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextConfig.applicationContext = applicationContext;
    }

    public static <T> T get(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
