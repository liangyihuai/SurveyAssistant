package com.huai.assist.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Interner;
import com.huai.assist.pojo.IPAddr;
import com.huai.assist.repository.IPAddrMapper;
import com.huai.assist.service.IPAddrService;
import com.huai.assist.utils.AddressUtils;
import com.huai.assist.utils.IPUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by liangyh on 11/5/16.
 */
@Component("IPAddrService")
public class IPAddrServiceImpl implements IPAddrService, InitializingBean{

    @Autowired
    private IPAddrMapper iPAddrMapper;


    public int currentVisitingCount() {
        List<String> ips = IPUtils.getRemoteIPAddr(80);
        if(ips == null){
            return 0;
        }
        return ips.size();
    }

    public int totalCount() {
        try {
            return totalCountCache.get("key");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<String, Integer> getCountGroupByAddr() {
        try {
            return countGroupByAddrCache.get("key");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new HashMap<String, Integer>(0);
    }

    /**
     * guava缓存
     */
    private final LoadingCache<String, Map<String, Integer>> countGroupByAddrCache = CacheBuilder.newBuilder()
            .maximumSize(300).expireAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<String, Map<String, Integer>>() {

                public Map<String, Integer> load(String s) throws Exception {
                    List<Map<String,Object>> mapList = iPAddrMapper.getCountGroupByAddr();

                    if(mapList.size() == 0)return new HashMap<String, Integer>(0);

                    Map<String, Integer> resultMap = new HashMap<String, Integer>(mapList.size());

                    Iterator<Map<String, Object>> listIter = mapList.listIterator();
                    while(listIter.hasNext()){
                        Map<String, Object> tempMap = listIter.next();

                        String key = (String)tempMap.get("addr");
                        Integer value = ((Long)tempMap.get("addrCount")).intValue();

                        resultMap.put(key, value);
                    }
                    return resultMap;
                }
            });

    /**
     * guava缓存
     */
    private final LoadingCache<String, Integer> totalCountCache = CacheBuilder.newBuilder()
            .maximumSize(300).expireAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<String, Integer>() {
                public Integer load(String key) throws Exception {
                    return iPAddrMapper.totalCount();
                }
            });

    public List<String> getAllIPAdds(){
        return iPAddrMapper.getAllIPs();
    }

    @Scheduled(cron="0/20 * *  * * ? ")   //每15秒执行一次
    public void saveIPsByIPStrs() {
        List<String> ips = IPUtils.getRemoteIPAddr(80);
        saveIPsByIPStrs(ips);
    }

    public int saveIPsByIPStrs(List<String> ips){
        List<IPAddr> list = new ArrayList<IPAddr>();
        for(String ip : ips){
            IPAddr newIpAddr = getIPAddr(ip);

            if(newIpAddr != null){
                list.add(newIpAddr);
            }
            ipSet.add(ip);
        }
        if(list.size() > 0){
            return saveIPsByIPAddrs(list);
        }
        return 0;
    }

    /**
     * get ipAddr , if it  already exists or can not get ip addr,
     * the method will return null;
     * @param ip
     * @return
     */
    public IPAddr getIPAddr(String ip){
        if(containsIPAddr(ip)) return null;

        String addr = AddressUtils.getAddressByIP(ip);
        if(addr != null && !"".equals(addr.trim())){
            IPAddr ipAddr = new IPAddr();
            ipAddr.setIp(ip);
            ipAddr.setRemark(addr);

            return ipAddr;
        }
        return null;
    }

    public int saveIPsByIPAddrs(List<IPAddr> ipAddrs) {
        return this.iPAddrMapper.saveIPs(ipAddrs);
    }

    private static final Set<String> ipSet = new HashSet<String>();
    private boolean containsIPAddr(String ip){
        if(ipSet.contains(ip)){
            return true;
        }
        return false;
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("---------initial ip set after properties set!-------");
        List<String> ips = getAllIPAdds();
        for(String ip: ips){
            ipSet.add(ip);
        }
    }
}
