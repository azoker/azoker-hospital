package com.azoker.utils;

import com.azoker.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by zxd on 2023/7/10
 */
@Slf4j
public class WebUtils {


    public static Result getResult(BindingResult bindingResult) {
        log.info("校验不通过");
        //获取所有的校验信息
        List<ObjectError> errors = bindingResult.getAllErrors();
        StringBuilder sb=new StringBuilder();
        for(ObjectError error:errors){
            String message = error.getDefaultMessage();
            sb.append(message+",");
        }
        log.info("具体的错误原因:{}",sb.toString());
        return Result.buildFail(50000,sb.toString().substring(0,sb.toString().length()-1));
    }


}
