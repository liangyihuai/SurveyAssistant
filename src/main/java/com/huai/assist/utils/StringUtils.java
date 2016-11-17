package com.huai.assist.utils;

import com.huai.assist.pojo.SystemInfo;

import java.io.RandomAccessFile;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

/**
 * Created by liangyh on 11/17/16.
 */
public class StringUtils {

    public static String getUniqueExcelName(){
        Random random = new Random();
        String result = "survey"+random.nextLong()+System.currentTimeMillis();
        return result;
    }
}
