package com.huai.assist.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by liangyh on 10/12/16.
 */
public class SystemInfoUtils {

    /**
     *
     * 思路如下：Linux系统中可以用top命令查看进程使用CPU和内存情况，通过Runtime类的exec()方法系统命令"top”，
     * 获取"top"的输出，从而得到CPU和内存的使用情况。对本程序稍加改动，还可以得到内存的使用情况。
     *
     * @return
     * @throws Exception
     */
    private static final int COUNT = 26;
    public static String getCUP_Memory_Thread_Info() {
        BufferedReader in = null;
        StringBuilder builder = new StringBuilder();
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("top -b -n 1");// 调用系统的“top"命令

            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = null;
            int count = 0;
            while ((str = in.readLine()) != null && count < COUNT) {
                builder.append(str);
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
