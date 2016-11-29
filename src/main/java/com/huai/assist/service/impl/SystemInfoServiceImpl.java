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
        int io = (int)(Math.random())*100;
        int swap = (int)(Math.random()*5) + 5;

        String info = "";

        if(flag++ == 1){
            info = info1;
        }else if(flag++ == 2){
            info = info2;
        }else{
            info = info3;
            flag++;
        }

        result.put("cpu", String.valueOf(cpu));
        result.put("memory", String.valueOf(memory));
        result.put("io", String.valueOf(io));
        result.put("swap", String.valueOf(swap));
        result.put("process", info);
        return result;
    }

    private static int flag = 1;

    private final static String info1 =
            "  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND\n" +
                    "    1 root      20   0   35048   4524   2992 S 0.000 0.443   0:31.76 systemd\n" +
                    "    2 root      20   0       0      0      0 S 0.000 0.000   0:00.00 kthreadd\n" +
                    "    3 root      20   0       0      0      0 S 0.000 0.000   0:02.90 ksoftirqd+\n" +
            "    5 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/0+\n" +
            "    7 root      20   0       0      0      0 S 0.000 0.000   0:05.24 rcu_sched\n" +
            "    8 root      20   0       0      0      0 S 0.000 0.000   0:09.00 rcuos/0\n" +
            "    9 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_bh\n" +
            "   10 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuob/0\n";

    private static final String info2 = "  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND\n" +
            "    1 root      20   0   35048   4524   2992 S 0.000 0.443   0:31.77 systemd\n" +
            "    5 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/0+\n" +
            "    3 root      20   0       0      0      0 S 0.000 0.000   0:02.90 ksoftirqd+\n" +
            "    8 root      20   0       0      0      0 S 0.000 0.000   0:09.01 rcuos/0\n" +
            "    2 root      20   0       0      0      0 S 0.000 0.000   0:00.00 kthreadd\n" +
            "    7 root      20   0       0      0      0 S 0.000 0.000   0:05.24 rcu_sched\n" +
            "    9 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_bh\n" +
            "   10 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuob/0\n";

    private static final String info3 ="  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND\n" +
            "    1 root      20   0   35048   4524   2992 S 0.000 0.443   0:31.78 systemd\n" +
            "    2 root      20   0       0      0      0 S 0.000 0.000   0:00.00 kthreadd\n" +
            "    3 root      20   0       0      0      0 S 0.000 0.000   0:02.90 ksoftirqd+\n" +
            "    5 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/0+\n" +
            "    7 root      20   0       0      0      0 S 0.000 0.000   0:05.25 rcu_sched\n" +
            "    8 root      20   0       0      0      0 S 0.000 0.000   0:09.01 rcuos/0\n" +
            "    9 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_bh\n" +
            "   10 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuob/0\n";
}
