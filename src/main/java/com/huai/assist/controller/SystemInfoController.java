package com.huai.assist.controller;

import com.huai.assist.pojo.SystemInfo;
import com.huai.assist.service.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by liangyh on 10/25/16.
 */
@Controller
@RequestMapping(value = "/system")
public class SystemInfoController {

    @Autowired
    private SystemInfoService systemInfoService;

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public SystemInfo getSystemInfo(){
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setTime(new java.util.Date());
        systemInfo.setTopInfo(this.systemInfoService.getTopInfo());

        this.systemInfoService.save(systemInfo);

        return systemInfo;
    }

    public List<SystemInfo> searchSystemInfo(String timeStr, int pageSize, int currentPage){
        return this.systemInfoService.search(timeStr, pageSize, currentPage);
    }

}
