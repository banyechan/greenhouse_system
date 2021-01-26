package com.douzi.greenhouse_system.serviceImpl;

import com.douzi.greenhouse_system.entity.GreenhouseMonitorData;
import com.douzi.greenhouse_system.mapper.GreenhouseMonitorDataMapper;
import com.douzi.greenhouse_system.service.GreenhouseMonitorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GreenhouseMonitorDataServiceImpl implements GreenhouseMonitorDataService {

    @Autowired
    private GreenhouseMonitorDataMapper greenhouseMonitorDataMapper;

    @Override
    public GreenhouseMonitorData getTheNewest() {
        return greenhouseMonitorDataMapper.selectTheFirst();
    }

    @Override
    public List<GreenhouseMonitorData> listHistory(Map<String,Object> map) {
        return greenhouseMonitorDataMapper.listByMap(map);
    }

    @Override
    @Async
    public void register() {
        System.out.println("---------异步执行----------");
        System.out.println("线程id：" + Thread.currentThread().getId());
        System.out.println("线程名称：" + Thread.currentThread().getName());

    }
}
