package com.douzi.greenhouse_system.controller;

import com.douzi.greenhouse_system.entity.GreenhouseMonitorData;
import com.douzi.greenhouse_system.service.GreenhouseMonitorDataService;
import com.douzi.greenhouse_system.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private GreenhouseMonitorDataService greenhouseMonitorDataService;

    //项目初建时，测试用例，可删
    @RequestMapping("/index")
    public String welcome(){
        String result = "春江潮水连海平，海上明月共潮生。";
        return result;
    }


    @GetMapping("/now")
    public R getNewData(){
        GreenhouseMonitorData result = greenhouseMonitorDataService.getTheNewest();
        return new R(result);
    }


    @GetMapping("/history")
    public R listHistory(){
        List<GreenhouseMonitorData> list = greenhouseMonitorDataService.listHistory();
        return new R(list);
    }




}
