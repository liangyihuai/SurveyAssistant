package com.huai.assist.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liangyh on 10/25/16.
 */
public class DateUtils {

    public static Date parseStrToDate(String str){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date result = null;
        try {
            result = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
