package com.azoker.admin.interceptor;

import cn.hutool.jwt.JWT;
import com.azoker.pojo.entity.Admin;
import com.azoker.result.Result;
import com.azoker.utils.JsonUtils;
import com.azoker.utils.ResponseUtils;
import com.azoker.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zxd on 2023/7/10
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    //处理器执行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.获取token
        log.info("认证拦截器被调用了...");
        String token = request.getHeader("token");

        if(StringUtils.isEmpty(token)){
            token = request.getParameter("token");

            if(StringUtils.isEmpty(token)){
                log.info("客户端没有携带token:{}",request.getRemoteAddr());
                String json = JsonUtils.objectToJson(Result.buildFail(20003, "没有携带token"));
                ResponseUtils.responseToJson(json,response);
                return false;
            }
        }


        //2.校验
        if(!TokenUtils.check(token)){
            log.info("无效token:{}",token);
            String json = JsonUtils.objectToJson(Result.buildFail(20003, "无效token"));
            ResponseUtils.responseToJson(json,response);
            return false;
        }

        //3.获取载荷数据
        JWT jwt = JWT.of(token);
        Long  expireTime = (Long) jwt.getPayload("expire_time");
        if(System.currentTimeMillis()>expireTime){
            log.info("登录失效:{}",token);
            String json = JsonUtils.objectToJson(Result.buildFail(20003, "登录失效"));
            ResponseUtils.responseToJson(json,response);
            return false;
        }

        //取出其它的载荷数据
        Admin admin=new Admin();
        admin.setId(Long.valueOf((String) jwt.getPayload("id")));
        admin.setUsername((String) jwt.getPayload("username"));
        admin.setPermissionTags((String) jwt.getPayload("permissionListTags"));
        admin.setName((String) jwt.getPayload("name"));
        admin.setPhone((String) jwt.getPayload("phone"));
        admin.setImageUrl((String) jwt.getPayload("imageUrl"));

        AdminThreadLocal.setAdmin(admin);


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
          AdminThreadLocal.remove();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
