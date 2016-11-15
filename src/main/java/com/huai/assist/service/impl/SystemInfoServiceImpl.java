package com.huai.assist.service.impl;


import com.huai.assist.pojo.SystemInfo;
import com.huai.assist.pojo.SystemInfoSearchCondition;
import com.huai.assist.repository.SystemInfoMapper;
import com.huai.assist.service.SystemInfoService;
import com.huai.assist.utils.DateUtils;
import com.huai.assist.utils.SystemInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<SystemInfo> search(SystemInfoSearchCondition condition) {
        if(condition == null || condition.getTimeStr() == null)return null;

        if(condition.getTimeStr().length() == 10){
            return systemInfoMapper.searchWithinDay(condition);
        }else if(condition.getTimeStr().length() == 12 || condition.getTimeStr().length() == 13){
            return systemInfoMapper.searchWithinHour(condition);
        }else if(condition.getTimeStr().length() == 15 || condition.getTimeStr().length() == 16){
            return systemInfoMapper.searchWithinMinute(condition);
        }else{
            return null;
        }
    }

    public int save(SystemInfo systemInfo){
        if(systemInfo == null)return 0;
        return systemInfoMapper.save(systemInfo);
    }
}
