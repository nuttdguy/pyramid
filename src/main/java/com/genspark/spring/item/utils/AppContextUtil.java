package com.genspark.spring.item.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppContextUtil implements ApplicationContextAware {

    private static ApplicationContext appContext;

    public AppContextUtil(ApplicationContext appContext) {
        AppContextUtil.appContext = appContext;
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppContextUtil.appContext = applicationContext;
    }
}
