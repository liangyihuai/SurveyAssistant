package com.huai.assist.utils;

import com.huai.assist.pojo.Respondent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liangyh on 11/17/16.
 */
public class PropertyUtils {

    public static List<List<String>> exchangePropertyToList(List<Respondent> respondents){
        if(respondents == null)return new ArrayList<List<String>>(0);

        List<List<String>> result = new ArrayList<List<String>>(respondents.size());

        Iterator<Respondent> iter = respondents.iterator();
        while(iter.hasNext()){
            List<String> list = new ArrayList<String>();
            Respondent respondent = iter.next();

            list.add(respondent.getName());
            list.add(respondent.getNationality());
            list.add(respondent.getSchool());
            list.add(respondent.getEducation());
            list.add(respondent.getMajor());
            list.add(respondent.getGender());
            list.add(respondent.getResidence());
            list.add(String.valueOf(respondent.getSurveyFinish()));

            result.add(list);
        }
        return result;
    }
}













