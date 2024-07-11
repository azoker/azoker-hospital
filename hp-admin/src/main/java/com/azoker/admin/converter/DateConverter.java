package com.azoker.admin.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        try{
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = df.parse(source);
            return date;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("日期转换失败!");
        }
    }
}
