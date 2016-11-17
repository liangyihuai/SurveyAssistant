package com.huai.assist.service;

import com.huai.assist.pojo.Page;
import com.huai.assist.pojo.Respondent;
import com.huai.assist.pojo.RespondentSearchCondition;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by liangyh on 11/8/16.
 */
public interface RespondentService {

    Page<List<Respondent>> search(RespondentSearchCondition condition);
    void download(RespondentSearchCondition condition, OutputStream outputStream);
}
