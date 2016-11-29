package com.huai.assist.service;

import com.huai.assist.pojo.IPAddr;

import java.util.List;
import java.util.Map;

/**
 * Created by liangyh on 11/5/16.
 */
public interface IPAddrService {
    int currentVisitingCount();

    int totalCount();


    void saveIPsByIPStrs();

    /**
     *
     * @return the key indicates address, the value indicates count;
     */
    Map<String, Integer> getCountGroupByAddr();

    List<String> getIps(String addr);
}
