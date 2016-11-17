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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liangyh on 10/20/16.
 */
@Component("SystemInfoService")
public class SystemInfoServiceImpl implements SystemInfoService {

    @Autowired
    private SystemInfoMapper systemInfoMapper;

    /*@Scheduled(cron="0/5 * *  * * ? ")   //每10秒执行一次*/
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

    @Scheduled(cron="0/45 * *  * * ? ")   //每xx秒执行一次
    private void save(){
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setTopInfo(getTopInfo());
        save(systemInfo);
    }

    public int save(SystemInfo systemInfo){
        if(systemInfo == null)return 0;
        return systemInfoMapper.save(systemInfo);
    }
}
