package com.huai.assist.service.impl;


import com.huai.assist.pojo.SystemInfo;
import com.huai.assist.repository.SystemInfoMapper;
import com.huai.assist.service.SystemInfoService;
import com.huai.assist.utils.DateUtils;
import com.huai.assist.utils.SystemInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    public List<SystemInfo> search(String dateStr, int pageSize, int currentPage) {
        Date date = DateUtils.parseStrToDate(dateStr);
        return systemInfoMapper.search(date, pageSize, currentPage);
    }

    public int save(SystemInfo systemInfo){
        if(systemInfo == null)return 0;
        return systemInfoMapper.save(systemInfo);
    }
}
