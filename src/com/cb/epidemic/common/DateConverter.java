package com.cb.epidemic.common;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换器（将界面中拿到的字符串转换成日期）
 */
@Component
public class DateConverter implements Converter<String, Date> {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//举例：2020-03-15；
    @Override
    public Date convert(String s) {
        if(s == null || s.length()==0){
            return  null;
        }
        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("转换提交的参数"+s+"为日期时出差"+e.getMessage());
        }
        return date;
    }
}
