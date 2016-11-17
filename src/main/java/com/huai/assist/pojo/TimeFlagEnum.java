package com.huai.assist.pojo;

/**
 * Created by liangyh on 11/17/16.
 */
public enum TimeFlagEnum {
    DAY(1, "DAY"),
    HOUR(2, "HOUR"),
    MINUTE(3, "MINUTE");

    private int timeFlag = 0;
    private String msg = "";

    TimeFlagEnum(int timeFlag, String msg){
        this.timeFlag = timeFlag;
        this.msg = msg;
    }

    public int getTimeFlag() {
        return timeFlag;
    }

    public String getMsg() {
        return msg;
    }
}
