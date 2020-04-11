package com.douzi.greenhouse_system.serviceImpl;

import com.douzi.greenhouse_system.entity.GreenhouseMonitorData;
import com.douzi.greenhouse_system.mapper.GreenhouseMonitorDataMapper;
import com.douzi.greenhouse_system.service.GreenhouseMonitorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreenhouseMonitorDataServiceImpl implements GreenhouseMonitorDataService {

    @Autowired
    private GreenhouseMonitorDataMapper greenhouseMonitorDataMapper;

    @Override
    public GreenhouseMonitorData getTheNewest() {
        return greenhouseMonitorDataMapper.selectTheFirst();
    }

    @Override
    public List<GreenhouseMonitorData> listHistory() {
        return greenhouseMonitorDataMapper.listHistory();
    }
}
