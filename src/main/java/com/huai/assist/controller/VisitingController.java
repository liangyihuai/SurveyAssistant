package com.huai.assist.controller;

import com.huai.assist.service.IPAddrService;
import com.huai.assist.utils.AddressUtils;
import com.huai.assist.utils.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by liangyh on 11/8/16.
 */
@Controller
@RequestMapping("/visiting")
public class VisitingController {

    @Autowired
    IPAddrService iPAddrService;

    @ResponseBody
    @RequestMapping(value = "/totalCount", method = RequestMethod.GET)
    public int getTotalVisitedCount(){
        int result = iPAddrService.totalCount();
        System.err.println("---------------"+result);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/onlineCount", method = RequestMethod.GET)
    public int getCurrentVisitingCount(){
        return iPAddrService.currentVisitingCount();
    }

    @ResponseBody
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public Map<String, Integer> statisticIPAddr(){
        return iPAddrService.getCountGroupByAddr();
    }

}
