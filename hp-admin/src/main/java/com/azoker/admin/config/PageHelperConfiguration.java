package com.azoker.admin.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by zxd on 2023/8/9
 */
@Configuration
public class PageHelperConfiguration {

    @Bean
    public PageInterceptor pageInterceptor(){
        //分页插件
        PageInterceptor pageInterceptor=new PageInterceptor();
        Properties properties=new Properties();
        properties.setProperty("param1","value1");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }


}
