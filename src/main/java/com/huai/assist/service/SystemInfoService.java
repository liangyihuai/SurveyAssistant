package com.huai.assist.service;

import com.huai.assist.pojo.SystemInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liangyh on 10/20/16.
 */
public interface SystemInfoService {

    String getTopInfo();

    List<SystemInfo> search(String dateStr, int pageSize, int currentPage);

    int save(SystemInfo systemInfo);
}
