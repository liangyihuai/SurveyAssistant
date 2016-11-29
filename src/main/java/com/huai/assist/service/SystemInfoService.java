package com.huai.assist.service;

import com.huai.assist.pojo.Page;
import com.huai.assist.pojo.SystemInfo;
import com.huai.assist.pojo.SystemInfoSearchCondition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by liangyh on 10/20/16.
 */
public interface SystemInfoService {

    String getTopInfo();
    Map<String, String> getTopInfo2();

    Page<List<SystemInfo>> search(SystemInfoSearchCondition condition);

    int save(SystemInfo systemInfo);
}
