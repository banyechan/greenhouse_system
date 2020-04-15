package com.douzi.greenhouse_system.service;


import com.douzi.greenhouse_system.entity.GreenhouseMonitorData;

import java.util.List;
import java.util.Map;

public interface GreenhouseMonitorDataService {

    // 查询出最新的一条记录
    GreenhouseMonitorData getTheNewest();

    // 查询历史记录
    List<GreenhouseMonitorData> listHistory(Map<String,Object> map);

}
