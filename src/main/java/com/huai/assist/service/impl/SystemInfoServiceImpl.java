package com.huai.assist.service.impl;


import com.huai.assist.pojo.Page;
import com.huai.assist.pojo.SystemInfo;
import com.huai.assist.pojo.SystemInfoSearchCondition;
import com.huai.assist.pojo.TimeFlagEnum;
import com.huai.assist.repository.SystemInfoMapper;
import com.huai.assist.service.SystemInfoService;
import com.huai.assist.utils.DateUtils;
import com.huai.assist.utils.SystemInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by liangyh on 10/20/16.
 */
@Component("SystemInfoService")
public class SystemInfoServiceImpl implements SystemInfoService {

    @Autowired
    private SystemInfoMapper systemInfoMapper;

    public String getTopInfo() {
        return SystemInfoUtils.getCUP_Memory_Thread_Info();
    }

    /**
     * yyyy-MM-dd hh:mm:ss
     *
     * @return
     */

    public Page<List<SystemInfo>> search(SystemInfoSearchCondition condition) {
        if(condition == null || condition.getTimeStr() == null)return null;

        Page<List<SystemInfo>> result = null;
        if(condition.getPage() != null){
            result = condition.getPage();
        }else{
            result = new Page<List<SystemInfo>>();
        }

        TimeFlagEnum timeFlag = null;
        if(condition.getTimeStr().length() == 10){
            timeFlag = TimeFlagEnum.DAY;
        }else if(condition.getTimeStr().length() == 12 || condition.getTimeStr().length() == 13){
            timeFlag = TimeFlagEnum.HOUR;
        }else if(condition.getTimeStr().length() == 15 || condition.getTimeStr().length() == 16){
            timeFlag = TimeFlagEnum.MINUTE;
        }else{
            return new Page<List<SystemInfo>>();
        }
        result.setItemCount(systemInfoMapper.count(condition, timeFlag.getTimeFlag()));
        result.setData(systemInfoMapper.search(condition, timeFlag.getTimeFlag()));

        return result;
    }

    /*@Scheduled(cron="0/45 * *  * * ? ")   //每xx秒执行一次*/
    private void save(){
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setTopInfo(getTopInfo());
        save(systemInfo);
    }

    public int save(SystemInfo systemInfo){
        if(systemInfo == null)return 0;
        return systemInfoMapper.save(systemInfo);
    }

    public Map<String, String> getTopInfo2(){
        return parseTopInfo(null);
    }

    private Map<String, String> parseTopInfo(String str){
        Map<String, String> result = new HashMap<>();
        int cpu = (int)(Math.random()*10) + 20;
        int memory = (int)(Math.random()*30) + 20;
        int io = (int)(Math.random()*100);
        int swap = (int)(Math.random()*5) + 5;

        String info = "";

        if(flag == 1){
            flag++;
            info = info1;
        }else if(flag++ == 2){
            flag++;
            info = info2;
        }else{
            info = info3;
            flag++;
        }
        if(flag > 3)flag = 1;

        result.put("cpu", String.valueOf(cpu));
        result.put("memory", String.valueOf(memory));
        result.put("io", String.valueOf(io));
        result.put("swap", String.valueOf(swap));
        result.put("process", info);
        return result;
    }

    private static int flag = 1;

    private final static String info1 = "PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND    \n" +
            " 2312 youya   20   0 6022128 1.042g  79808 S 6.667 13.54   6:26.18 java       \n" +
            "    1 root      20   0   33800   5908   3416 S 0.000 0.073   0:06.68 systemd    \n" +
            "    2 root      20   0       0      0      0 S 0.000 0.000   0:00.00 kthreadd   \n" +
            "    3 root      20   0       0      0      0 S 0.000 0.000   0:00.00 ksoftirqd+ \n" +
            "    5 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/0+ \n" +
            "    6 root      20   0       0      0      0 S 0.000 0.000   0:00.43 kworker/u+ \n" +
            "    7 root      20   0       0      0      0 S 0.000 0.000   0:01.49 rcu_preem+ \n" +
            "    8 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_sched  \n" +
            "    9 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_bh     \n" +
            "   10 root      20   0       0      0      0 S 0.000 0.000   0:00.32 rcuop/0    \n" +
            "   11 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuos/0    \n" +
            "   12 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuob/0    \n" +
            "   13 root      rt   0       0      0      0 S 0.000 0.000   0:00.08 migration+ \n" +
            "   14 root      rt   0       0      0      0 S 0.000 0.000   0:00.00 watchdog/0 \n" +
            "   15 root      rt   0       0      0      0 S 0.000 0.000   0:00.00 watchdog/1 \n" +
            "   16 root      rt   0       0      0      0 S 0.000 0.000   0:00.04 migration+ \n" +
            "   17 root      20   0       0      0      0 S 0.000 0.000   0:00.00 ksoftirqd+ \n" +
            "   19 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/1+ \n" +
            "   20 root      20   0       0      0      0 S 0.000 0.000   0:00.12 rcuop/1 ";

    private static final String info2 = " PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND    \n" +
            " 1586 root      20   0  277428  36724  22932 S 6.667 0.455   0:16.89 Xorg       \n" +
            " 3269 youya   20   0  544336  36196  25956 S 6.667 0.449   0:00.80 gnome-ter+ \n" +
            "    1 root      20   0   33800   5908   3416 S 0.000 0.073   0:06.68 systemd    \n" +
            "    2 root      20   0       0      0      0 S 0.000 0.000   0:00.00 kthreadd   \n" +
            "    3 root      20   0       0      0      0 S 0.000 0.000   0:00.00 ksoftirqd+ \n" +
            "    5 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/0+ \n" +
            "    6 root      20   0       0      0      0 S 0.000 0.000   0:00.44 kworker/u+ \n" +
            "    7 root      20   0       0      0      0 S 0.000 0.000   0:01.62 rcu_preem+ \n" +
            "    8 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_sched  \n" +
            "    9 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_bh     \n" +
            "   10 root      20   0       0      0      0 S 0.000 0.000   0:00.34 rcuop/0    \n" +
            "   11 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuos/0    \n" +
            "   12 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuob/0    \n" +
            "   13 root      rt   0       0      0      0 S 0.000 0.000   0:00.08 migration+ \n" +
            "   14 root      rt   0       0      0      0 S 0.000 0.000   0:00.00 watchdog/0 \n" +
            "   15 root      rt   0       0      0      0 S 0.000 0.000   0:00.00 watchdog/1 \n" +
            "   16 root      rt   0       0      0      0 S 0.000 0.000   0:00.04 migration+ \n" +
            "   17 root      20   0       0      0      0 S 0.000 0.000   0:00.00 ksoftirqd+ \n" +
            "   19 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/1+ \n" +
            "   20 root      20   0       0      0      0 S 0.000 0.000   0:00.13 rcuop/1";

    private static final String info3 =" PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND    \n" +
            " 2312 youya   20   0 6022220 1.053g  79900 S 5.333 13.68   6:55.93 java       \n" +
            " 1586 root      20   0  276340  37100  23308 S 0.667 0.460   0:17.81 Xorg       \n" +
            "    7 root      20   0       0      0      0 S 0.333 0.000   0:01.68 rcu_preem+ \n" +
            "   28 root      20   0       0      0      0 S 0.333 0.000   0:01.13 rcuop/2    \n" +
            "  599 root      20   0       0      0      0 S 0.333 0.000   0:00.55 kworker/u+ \n" +
            " 1007 root      20   0  420724  14684  12268 S 0.333 0.182   0:01.35 NetworkMa+ \n" +
            " 1248 root      20   0  223820  10456   7344 S 0.333 0.130   0:00.05 upowerd    \n" +
            " 2583 youya   20   0  866320 197652  82680 S 0.333 2.450   0:09.13 firefox    \n" +
            " 3250 root      20   0       0      0      0 S 0.333 0.000   0:00.02 kworker/u+ \n" +
            "    1 root      20   0   33800   5908   3416 S 0.000 0.073   0:06.68 systemd    \n" +
            "    2 root      20   0       0      0      0 S 0.000 0.000   0:00.00 kthreadd   \n" +
            "    3 root      20   0       0      0      0 S 0.000 0.000   0:00.00 ksoftirqd+ \n" +
            "    5 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/0+ \n" +
            "    6 root      20   0       0      0      0 S 0.000 0.000   0:00.44 kworker/u+ \n" +
            "    8 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_sched  \n" +
            "    9 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_bh     \n" +
            "   10 root      20   0       0      0      0 S 0.000 0.000   0:00.34 rcuop/0    \n" +
            "   11 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuos/0    \n" +
            "   12 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuob/0    \n" +
            "   13 root      rt   0       0      0      0 S 0.000 0.000   0:00.08 migration+ \n" +
            "   14 root      rt   0       0      0      0 S 0.000 0.000   0:00.00 watchdog/0 \n" +
            "   15 root      rt   0       0      0      0 S 0.000 0.000   0:00.00 watchdog/1 \n" +
            "   16 root      rt   0       0      0      0 S 0.000 0.000   0:00.04 migration+ \n" +
            "   17 root      20   0       0      0      0 S 0.000 0.000   0:00.00 ksoftirqd+ \n" +
            "   19 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/1+ \n" +
            "   20 root      20   0       0      0      0 S 0.000 0.000   0:00.13 rcuop/1 ";
}
