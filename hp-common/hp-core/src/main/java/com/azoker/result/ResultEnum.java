package com.azoker.result;

/**
 * Created by zxd on 2023/7/11
 */
public enum ResultEnum {

    UN_KNOW_EXCEPTION(20000,"未知异常"),
    USERNAME_OR_PASSWORD_ERROR(20001,"用户名或密码错误"),
    TWO_PASSWORD_ERROR(20002,"两次密码输入不一致"),
    USERNAME_EXITS(20003,"用户名已经存在"),
    PHONE_EXITS(20004,"电话已经存在"),
    EMAIL_EXITS(20005,"邮箱已经存在"),
    NO_PERMISSION(20006,"没有权限"),
    NO_DATA(20007,"没有找到数据"),
    OPTION_FAIL(50000,"操作失败"),
    DOCTOR_EXITS(20008,"该医生当日时间段已安排出诊计划"),
    PLANT_TIME_ERROR(20009,"排班时间不能小于当前时间");

    private Integer code;

    private String message;

    private ResultEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
