package com.douzi.greenhouse_system.controller;

import com.alibaba.fastjson.JSONObject;
import com.douzi.greenhouse_system.config.SystemCache;
import com.douzi.greenhouse_system.entity.GreenhouseMonitorData;
import com.douzi.greenhouse_system.entity.MiddleStudent;
import com.douzi.greenhouse_system.service.GreenhouseMonitorDataService;
import com.douzi.greenhouse_system.utils.DateUtil;
import com.douzi.greenhouse_system.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private GreenhouseMonitorDataService greenhouseMonitorDataService;



    private Map<String,Object> temCache = SystemCache.cacheMap;


    //项目初建时，测试用例，可删
    @RequestMapping("/index")
    public String welcome(){
        String result = "春江潮水连海平，海上明月共潮生。";
        return result;
    }

    @GetMapping(value = "/test")
    public Object test(){
        for (int i = 0; i < 10; i++) {
            greenhouseMonitorDataService.register();
        }
        System.out.println("主线程结束");
        return "OK";
    }




    //项目初建时，测试用例，可删
    @RequestMapping("/cache")
    public String getCache(@RequestParam("key") String key){
        String result = "春江潮水连海平，海上明月共潮生。";
        System.out.println("=========== temCache ==="  + temCache);
        log.info("temCache={}",temCache);
        if(temCache != null && temCache.containsKey(key)){
            temCache.put(key,"中华");
            result = temCache.get(key).toString();
        }


        MiddleStudent middleStudent = new MiddleStudent();
        middleStudent.setId(10);
        middleStudent.setAge(18);
        middleStudent.setName("杨超越");

        middleStudent.setGrade(2);
        middleStudent.setSchlool("中华艺术宫");

        log.info("----middleStudent={}",middleStudent);
        return result;
    }




    //查询最新的一条数据
    @GetMapping("/now")
    public R getNewData(){
        GreenhouseMonitorData result = greenhouseMonitorDataService.getTheNewest();
        return new R(result);
    }

    /**
     * 根据时间段，查询历史记录
     *   返回的数据结构，是为了满足前端页面数据展示的需要
     */
    @PostMapping("/history")
    public R listHistory(@RequestBody(required = false) GreenhouseMonitorData greenhouseMonitor) throws ParseException {
        List<JSONObject> result = new ArrayList<JSONObject>();
        Date startTime = null;
        Date endTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(greenhouseMonitor != null && greenhouseMonitor.getStartTime() != null
            && greenhouseMonitor.getEndTime() != null){
            startTime = greenhouseMonitor.getStartTime();
            endTime = greenhouseMonitor.getEndTime();
        }else{
            //如果没有输入时间,默认取最新一条记录，往前推24小时之间的数据
            GreenhouseMonitorData newestOne = greenhouseMonitorDataService.getTheNewest();
            if(newestOne != null){
                endTime = newestOne.getDatetime();
                startTime = DateUtil.getBeforHour(newestOne.getDatetime(),24);
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<GreenhouseMonitorData> list = greenhouseMonitorDataService.listHistory(map);
        if(list != null && list.size() > 0){
            List<JSONObject> airtempValueList = new ArrayList();
            List<JSONObject> airhumiValueList = new ArrayList();
            List<JSONObject> soiltempList = new ArrayList();
            List<JSONObject> soilhumiList = new ArrayList();
            List<JSONObject> co2List = new ArrayList();
            List<JSONObject> sundataList = new ArrayList();
            for (GreenhouseMonitorData tem : list){
                String dateTime = sdf.format(tem.getDatetime());

                JSONObject airtempValue = new JSONObject();
                airtempValue.put("key",dateTime);
                airtempValue.put("val",tem.getAirtemp());
                airtempValueList.add(airtempValue);

                JSONObject airhumiValue = new JSONObject();
                airhumiValue.put("key",dateTime);
                airhumiValue.put("val",tem.getAirhumi());
                airhumiValueList.add(airhumiValue);

                JSONObject soiltempValue = new JSONObject();
                soiltempValue.put("key",dateTime);
                soiltempValue.put("val",tem.getSoiltemp());
                soiltempList.add(soiltempValue);

                //土壤湿度 取三个湿度值的平均值
                double avgSoilHumi = (tem.getSoilhumi1() + tem.getSoilhumi2() + tem.getSoilhumi3()) / 3000;
                JSONObject soilhumi = new JSONObject();
                soilhumi.put("key",dateTime);
                soilhumi.put("val",String.format("%.2f", avgSoilHumi)); //保留两位小数
                soilhumiList.add(soilhumi);

                JSONObject co2Value = new JSONObject();
                co2Value.put("key",dateTime);
                co2Value.put("val",String.format("%.2f", (tem.getCo2()/10)));
                co2List.add(co2Value);

                JSONObject sundateValue = new JSONObject();
                sundateValue.put("key",dateTime);
                sundateValue.put("val",tem.getSundata());
                sundataList.add(sundateValue);
            }

            JSONObject airtemp = new JSONObject();
            airtemp.put("key","空气温度");
            airtemp.put("val",airtempValueList);
            result.add(airtemp);

            JSONObject airhumi = new JSONObject();
            airhumi.put("key","空气湿度");
            airhumi.put("val",airhumiValueList);
            result.add(airhumi);

            JSONObject soiltemp = new JSONObject();
            soiltemp.put("key","土壤温度");
            soiltemp.put("val",soiltempList);
            result.add(soiltemp);

            JSONObject soilhumi = new JSONObject();
            soilhumi.put("key","土壤湿度");
            soilhumi.put("val",soilhumiList);
            result.add(soilhumi);

            JSONObject co2 = new JSONObject();
            co2.put("key","co2");
            co2.put("val",co2List);
            result.add(co2);

            JSONObject sundata = new JSONObject();
            sundata.put("key","光照强度");
            sundata.put("val",sundataList);
            result.add(sundata);
        }

        return new R(result);
    }

}
