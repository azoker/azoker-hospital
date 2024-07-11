package com.azoker.exception;

import com.azoker.result.ResultEnum;

/**
 * Created by zxd on 2023/7/6
 */
public class CustomerException extends RuntimeException{

    private String message;

    private ResultEnum resultEnum;

    public CustomerException(ResultEnum resultEnum){
        this.resultEnum=resultEnum;
        this.message=resultEnum.getMessage();
    }

    public CustomerException(String message){
        super(message);
        this.message=message;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
