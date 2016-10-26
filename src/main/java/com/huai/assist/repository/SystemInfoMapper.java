package com.huai.assist.repository;

import com.huai.assist.pojo.SystemInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by liangyh on 10/25/16.
 */
public interface SystemInfoMapper {

    List<SystemInfo> search(@Param("date") Date date, @Param("pageSize") int pageSize, @Param("currentPage") int currentPage);

    int save(SystemInfo systemInfo);
}
