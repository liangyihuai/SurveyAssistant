package com.huai.assist.controller;

import com.huai.assist.pojo.Page;
import com.huai.assist.pojo.Respondent;
import com.huai.assist.pojo.RespondentSearchCondition;
import com.huai.assist.service.RespondentService;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by liangyh on 11/8/16.
 */
@Controller
@RequestMapping("/respondent")
public class RespondentController {

    @Autowired
    private RespondentService respondentService;

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<List<Respondent>> search(RespondentSearchCondition condition){
        return respondentService.search(condition);
    }

    @RequestMapping("/download")
    public void download(RespondentSearchCondition condition,
                         HttpServletResponse response) throws IOException {
        // 设置响应头，控制浏览器下载文件
        response.setContentType("application/vnd.ms-excel");
        //为了解决中文名称乱码问题
        String fileName=new String("survey.xlsx".getBytes("UTF-8"), "iso-8859-1");
        response.setHeader("content-disposition","attachment;filename=" + fileName);
        respondentService.download(condition, response.getOutputStream());
    }
}
