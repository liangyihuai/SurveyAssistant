package com.huai.assist.repository;

import com.huai.assist.pojo.SystemInfo;
import com.huai.assist.pojo.SystemInfoSearchCondition;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by liangyh on 10/25/16.
 */
public interface SystemInfoMapper {

    List<SystemInfo> searchWithinDay(SystemInfoSearchCondition condition);

    List<SystemInfo> searchWithinHour(SystemInfoSearchCondition condition);

    List<SystemInfo> searchWithinMinute(SystemInfoSearchCondition condition);

    int save(SystemInfo systemInfo);
}
