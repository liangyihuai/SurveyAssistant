package com.huai.assist.service.impl;

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

/**
 * Created by liangyh on 11/5/16.
 */
@Component
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
        return iPAddrMapper.totalCount();
    }

    public List<String> getAllIPAdds(){
        return iPAddrMapper.getAllIPs();
    }

    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    public void saveIPsByIPStrs() {
        List<String> ips = IPUtils.getRemoteIPAddr(80);
        System.out.println("-----------save ips By p strs--------");
//        String[] ipStr = {"114.80.68.233","111.13.101.208","202.96.128.86",
//                "59.35.122.17",""};
//
//        List<String> ips = new ArrayList<String>(1);
//        for(String ip: ipStr){
//            ips.add(ip);
//        }
        saveIPsByIPStrs(ips);
    }

    public int saveIPsByIPStrs(List<String> ips){
        List<IPAddr> list = new ArrayList<IPAddr>();
        for(String ip : ips){
            IPAddr newIpAddr = getIPAddr(ip);

            if(newIpAddr != null){
                ipSet.add(newIpAddr.getIp());
                list.add(newIpAddr);
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
    public IPAddr getIPAddr(String ip){
        if(containsIPAddr(ip)) return null;

        String addr = AddressUtils.getAddressByIP(ip);

        System.out.println("---------addr = "+addr+"-------------");

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

    public Map<String, Integer> getCountGroupByAddr() {
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

    public void afterPropertiesSet() throws Exception {
        System.out.println("---------initial ip set after properties set!-------");
        List<String> ips = getAllIPAdds();
        for(String ip: ips){
            ipSet.add(ip);
        }
    }
}
