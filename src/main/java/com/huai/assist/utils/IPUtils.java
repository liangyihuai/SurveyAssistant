package com.huai.assist.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangyh on 10/12/16.
 */
public class IPUtils {

    /**
     * get remote ip addr, which the local port in the connection is "localPort".
     * cmds[2] = "netstat -nt | grep ESTABLISHED|awk '$4 ~ /:" + localPort + "/ {split($5, arr, \":\"); print arr[1]}'|sort|uniq"
     * @param localPort
     * @return
     */
    public static List<String> getRemoteIPAddr(int localPort){
        String[] cmds = new String[3];
        cmds[0] = "/bin/bash";
        cmds[1] = "-c";
        cmds[2] = "netstat -nt | grep ESTABLISHED|awk '$4 ~ /:" + localPort + "/ {split($5, arr, \":\"); print arr[1]}'|sort|uniq";

        List<String> result = new ArrayList<String>();

        Runtime runtime = Runtime.getRuntime();
        BufferedReader in = null;
        try {
            Process process = runtime.exec(cmds);

            in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String str;
            while((str = in.readLine()) != null){
                result.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(in != null)in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
