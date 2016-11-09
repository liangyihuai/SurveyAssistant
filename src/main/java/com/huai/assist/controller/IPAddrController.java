package com.huai.assist.controller;

import com.huai.assist.service.IPAddrService;
import com.huai.assist.utils.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.huai.assist.utils.IPUtils.getRemoteIPAddr;

/**
 * Created by liangyh on 11/5/16.
 */
@Controller
@RequestMapping(value = "/ip")
public class IPAddrController {

    @Autowired
    private IPAddrService iPAddrService;

    @ResponseBody
    @RequestMapping(value = "/totalCount", method = RequestMethod.GET)
    public int getSurveyedCount(){
        return this.iPAddrService.totalCount();
    }

    @ResponseBody
    @RequestMapping(value = "/onlineCount")
    public int getOnlineCount(){
        List<String> ipAddrs = IPUtils.getRemoteIPAddr(80);

        return ipAddrs.size();


    }

}
