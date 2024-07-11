package com.azoker.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxd on 2023/7/5
 */
@Data
public class Result<T> implements Serializable {


    /**
     * 响应的状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 总记录数
     */
    private Long count;

    /**
     * 响应的数据
     */
    private T data;



    /**
     * 构建带分页的返回
     * @param data
     * @param count
     * @return
     */
    public static Result buildSuccess(Object data,Long count){


         Result result=new Result();
         result.setCode(0);
         result.setMsg("操作成功!");
         result.setData(data);
         result.setCount(count);
         return result;
    }

    /**
     * 构建不带总记录成功的返回
     * @param data
     * @return
     */
    public static  Result buildSuccess(Object data){
        return buildSuccess(data,null);
    }

    /**
     * 构建成功提示的消息
     * @return
     */
    public static Result buildSuccess(){
        return buildSuccess(null);
    }


    /**
     * 根据boolean的结果决定构建成功还是失败
     * @param result
     * @return
     */
    public static Result judge(Boolean result){
        return result==true?buildSuccess():buildFail(ResultEnum.OPTION_FAIL.getCode(), ResultEnum.OPTION_FAIL.getMessage());
    }

    /**
     * 构建失败的消息
     * @param code
     * @param msg
     * @return
     */
    public static Result buildFail(Integer code,String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
