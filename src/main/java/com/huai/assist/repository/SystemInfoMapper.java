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
    int count(@Param("condition") SystemInfoSearchCondition condition, @Param("flag") int timeFlag);
    List<SystemInfo> search(@Param("condition") SystemInfoSearchCondition condition, @Param("flag") int timeFlag);

    int save(SystemInfo systemInfo);
}
