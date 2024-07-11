package com.azoker.admin.exception;

import com.azoker.exception.CustomerException;
import com.azoker.result.Result;
import com.azoker.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zxd on 2024/3/17
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    //未知异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result  error(Exception e){
        log.error("未知异常:{}",e.getMessage());
        return Result.buildFail(ResultEnum.UN_KNOW_EXCEPTION.getCode(),ResultEnum.UN_KNOW_EXCEPTION.getMessage());
    }

    //自定义异常
    @ExceptionHandler(CustomerException.class)
    @ResponseBody
    public Result  customer(CustomerException e){
        log.error("自定义异常:{}",e.getMessage());
        return Result.buildFail(e.getResultEnum().getCode(),e.getResultEnum().getMessage());
    }

}
