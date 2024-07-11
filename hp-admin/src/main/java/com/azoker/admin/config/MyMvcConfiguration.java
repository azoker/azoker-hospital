package com.azoker.admin.config;

import com.azoker.admin.converter.DateConverter;
import com.azoker.admin.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by zxd on 2024/3/15
 */
@Configuration//springboot在启动的时候加载java配置，检测是否实现了WebMvcConfigurer接口
//如果实现了，自动调用addInterceptors往springmvc底层添加拦截器
public class MyMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }


    //注册拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/admin/login","/swagger-resources/**","/webjars/**","/v2/**","/swagger-ui.html/**");
    }

    //注册日期转换器
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }


}