package com.huai.assist.controller;

import com.huai.assist.pojo.Page;
import com.huai.assist.pojo.SystemInfo;
import com.huai.assist.pojo.SystemInfoSearchCondition;
import com.huai.assist.service.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by liangyh on 10/25/16.
 */
@Controller
@RequestMapping(value = "/sys")
public class SystemInfoController {

    @Autowired
    private SystemInfoService systemInfoService;

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public SystemInfo getSystemInfo(){
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setCreateTime(new java.util.Date());
        systemInfo.setTopInfo(this.systemInfoService.getTopInfo());

        return systemInfo;
    }

    @ResponseBody
    @RequestMapping(value = "/info2", method = RequestMethod.GET)
    public Map<String, String> getSystemInfo2(){
        return this.systemInfoService.getTopInfo2();
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public Page<List<SystemInfo>> searchSystemInfo(SystemInfoSearchCondition condition){
        return this.systemInfoService.search(condition);
    }

}
