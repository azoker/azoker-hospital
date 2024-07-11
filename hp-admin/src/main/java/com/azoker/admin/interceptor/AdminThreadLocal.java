package com.azoker.admin.interceptor;

import com.azoker.pojo.entity.Admin;

/**
 * Created by zxd on 2023/7/10
 */
public class AdminThreadLocal {

    private static ThreadLocal<Admin> tl=new ThreadLocal<>();

    /**
     * 绑定管理员到当前线程
     * @param admin
     */
    public static void setAdmin(Admin admin){
        tl.set(admin);
    }

    /**
     * 获取当前线程绑定的管理员信息
     * @return
     */
    public static Admin get(){
        return tl.get();
    }

    /**
     * 移除当前线程绑定的管理员信息
     */
    public static void remove(){
        tl.remove();
    }

}
