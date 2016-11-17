package com.huai.assist.service.impl;

import com.huai.assist.pojo.Page;
import com.huai.assist.pojo.Respondent;
import com.huai.assist.pojo.RespondentSearchCondition;
import com.huai.assist.repository.RespondentMapper;
import com.huai.assist.service.RespondentService;
import com.huai.assist.utils.ExcelFormUtil;
import com.huai.assist.utils.PropertyUtils;
import com.huai.assist.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by liangyh on 11/8/16.
 */
@Component("RespondentService")
public class RespondentServiceImpl implements RespondentService{

    @Autowired
    private RespondentMapper respondentMapper;

    public Page<List<Respondent>> search(RespondentSearchCondition condition) {
        if(condition == null)return null;

        Page<List<Respondent>> result = null;
        if(condition.getPage() != null){
            result = condition.getPage();
        }else{
            result = new Page<List<Respondent>>();
        }
        result.setItemCount(respondentMapper.getCount(condition));
        result.setData(respondentMapper.search(condition));
        return result;
    }

    public void download(RespondentSearchCondition condition, OutputStream outputStream) {
        if(condition == null)return ;
        condition.setPage(null);

        Page<List<Respondent>> page = search(condition);
        if(page == null)return ;

        ExcelFormUtil.export(PropertyUtils.exchangePropertyToList(page.getData()), outputStream);
    }
}
