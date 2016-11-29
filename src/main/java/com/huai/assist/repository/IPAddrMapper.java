package com.huai.assist.repository;

import com.huai.assist.pojo.IPAddr;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by liangyh on 11/5/16.
 */
public interface IPAddrMapper{

    int totalCount();

    int saveIPs(@Param("ipAdds") List<IPAddr> ipAddrs);

    List<IPAddr> getAllIPsAddr();

    List<Map<String, Object>> getCountGroupByAddr();

   /* List<String> getIps(@Param("addr") String addr);*/
}