package com.huai.assist.pojo;

import java.util.Date;

/**
 * Created by liangyh on 10/12/16.
 */
public class SystemInfo {
    private int id;
    private Date time;

    private String topInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTopInfo() {
        return topInfo;
    }

    public void setTopInfo(String topInfo) {
        this.topInfo = topInfo;
    }
}
