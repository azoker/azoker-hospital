package com.azoker.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.util.Map;


public class TokenUtils {

    private static final String SECRET="sdvsdvsdASFVASVGsasASVASSVSDVSDVDSasdvsgvdfhhgffhfghfghfghgfh";


    /**
     * 生成jwt令牌
     * @param map
     * @return
     */
    public static  String createToken(Map<String,Object> map){
        map.put("expire_time", System.currentTimeMillis() + 1000*60*60*12);
        String token = JWTUtil.createToken(map, SECRET.getBytes());
        return token;
    }


    /**
     * 验证
     * @param token
     * @return
     */
    public static  Boolean check(String token){
        return JWT.of(token).setKey(SECRET.getBytes()).verify();
    }


}
