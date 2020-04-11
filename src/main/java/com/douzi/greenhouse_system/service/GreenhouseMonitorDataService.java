package com.douzi.greenhouse_system.service;


import com.douzi.greenhouse_system.entity.GreenhouseMonitorData;

import java.util.List;

public interface GreenhouseMonitorDataService {

    GreenhouseMonitorData getTheNewest();

    List<GreenhouseMonitorData> listHistory();

}
