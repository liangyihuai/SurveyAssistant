package com.huai.assist.repository;

import com.huai.assist.pojo.Respondent;
import com.huai.assist.pojo.RespondentSearchCondition;

import java.util.List;

/**
 * Created by liangyh on 11/8/16.
 */
public interface RespondentMapper {

    int getCount(RespondentSearchCondition condition);
    List<Respondent> search(RespondentSearchCondition searchCondition);

}
