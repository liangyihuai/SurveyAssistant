package com.huai.assist.pojo;

import java.util.Date;

/**
 * Created by liangyh on 10/12/16.
 */
public class SystemInfo {
    private int id;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private String topInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopInfo() {
        return topInfo;
    }

    public void setTopInfo(String topInfo) {
        this.topInfo = topInfo;
    }

    @Override
    public String toString() {
        return "SystemInfo{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", topInfo='" + topInfo + '\'' +
                '}';
    }
}
