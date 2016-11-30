package com.huai.assist.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
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

    /*the key is IP, the value is address*/
    private static final Map<String, String> allIPsCache = new HashMap<>();

    /*the value is address, the key is IPs, it store the ip the host have known */
    private static final Map<String, Set<String>> onlineIpAddrCache = new HashMap<>();

    private static final String UNKNOWN = "UK";

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
     * query IPs by address
     */
    public List<String> getOnlineIps(String addr) {
        if(addr == null){
            return new ArrayList<String>(0);
        }

        Set<String> ips = getOnlineIPs(addr);
        List<String> result = new ArrayList<>();
        if(ips != null)result.addAll(ips);

        return result;
    }

    @Override
    public List<String> getIps(String addr) {
        if(addr == null)return new ArrayList<>(0);
        if(addr.length() > 2)addr = addr.substring(0, 2);
        return iPAddrMapper.getIps(addr);
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

    public List<IPAddr> getAllIPAdds(){
        return iPAddrMapper.getAllIPsAddr();
    }

    @Scheduled(cron="0/15 * *  * * ? ")   //每15秒执行一次
    public void saveIPsByIPStrs() {
//        List<String> ips = IPUtils.getRemoteIPAddr(80);

        List<String> ips = new ArrayList<>();
        ips.add("59.35.122.17");
        ips.add("59.35.122.16");
        ips.add("111.13.101.207");
        saveIPsByIPStrs(ips);
    }

    private int saveIPsByIPStrs(List<String> ips){
        List<IPAddr> list = new ArrayList<IPAddr>();
        onlineIpAddrCache.clear();
        for(String ip : ips){
            IPAddr newIpAddr = createAddrObjectNotExistsBefore(ip);

            if(newIpAddr != null){
                list.add(newIpAddr);
                allIPsCache.put(newIpAddr.getIp(), newIpAddr.getRemark());
                addOnlineIpAddr(newIpAddr.getRemark(), newIpAddr.getIp());
            }else{
                allIPsCache.put(ip, UNKNOWN);
                if(allIPsCache.containsKey(ip)){
                    addOnlineIpAddr(allIPsCache.get(ip), ip);
                }else{
                    addOnlineIpAddr(UNKNOWN, ip);
                }
            }
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
    private IPAddr createAddrObjectNotExistsBefore(String ip){
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

    private int saveIPsByIPAddrs(List<IPAddr> ipAddrs) {
        return this.iPAddrMapper.saveIPs(ipAddrs);
    }


    private boolean containsIPAddr(String ip){
        if(allIPsCache.containsKey(ip)){
            return true;
        }
        return false;
    }


    private void addOnlineIpAddr(String address, String ip){
        if(address == null|| ip == null)return ;

        if(address.length() > 2) address = address.substring(0, 2);

        if(onlineIpAddrCache.containsKey(address)){
            onlineIpAddrCache.get(address).add(ip);
        }
        else{
            Set<String> ips = new HashSet<>();
            ips.add(ip);
            onlineIpAddrCache.put(address, ips);
        }
    }

    private Set<String> getOnlineIPs(String addr){
        if(addr == null) return null;
        if(addr.length() > 2) addr = addr.substring(0, 2);
        return onlineIpAddrCache.get(addr);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("---------initial ip set after properties set!-------");
        List<IPAddr> ips = getAllIPAdds();
        for(IPAddr addr: ips){
            allIPsCache.put(addr.getIp(), addr.getRemark());
        }
    }

}
