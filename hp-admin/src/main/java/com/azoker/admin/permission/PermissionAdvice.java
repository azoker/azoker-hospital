package com.azoker.admin.permission;

import com.azoker.admin.interceptor.AdminThreadLocal;
import com.azoker.exception.CustomerException;
import com.azoker.result.ResultEnum;
import com.azoker.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by zxd on 2023/7/13
 */
@Aspect //切面
@Slf4j
public class PermissionAdvice {

    //拦截所有controller的所有方法
    @Around("execution(* com.azoker.admin.controller.*Controller.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        //1.获取目标方法上是否有权限注解，如果有权限注解进行权限鉴定，如果没有就直接放行

        //2.获取目标方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        //3.获取目标方法上是否有权限注解
        Permission permission = method.getAnnotation(Permission.class);

        //4.如果没有注解就直接放行
        if(permission==null){
            log.debug("{}方法不需要权限鉴定，直接放行...",method.getName());
            return joinPoint.proceed();
        }

        //5.如果是超级管理员直接放行
        if(AdminThreadLocal.get().getUsername().equals("admin")){
            log.debug("超级管理员直接放行...");
            return joinPoint.proceed();
        }

        //6.获取该注解的标记
        //doctor:list
        String tag = permission.value();

        //7.获取当前线程绑定用户的权限标记
        String tags = AdminThreadLocal.get().getPermissionTags();
        List<String> list = JsonUtils.jsonToList(tags, String.class);

        boolean isOk=false;

        for(String t:list){
            if(t.equals(tag)){
                isOk=true;
                break;
            }
        }


        if(!isOk){
            log.error("没有权限；{}",tag);
            throw new CustomerException(ResultEnum.NO_PERMISSION);
        }

        //有权限，直接放行
        log.debug("有权限，放行...");
        return joinPoint.proceed();
    }


}
