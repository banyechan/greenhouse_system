package com.douzi.greenhouse_system.controller;

import com.alibaba.fastjson.JSONObject;
import com.douzi.greenhouse_system.entity.GreenhouseMonitorData;
import com.douzi.greenhouse_system.service.GreenhouseMonitorDataService;
import com.douzi.greenhouse_system.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        List<JSONObject> result = new ArrayList<JSONObject>();
        List<GreenhouseMonitorData> list = greenhouseMonitorDataService.listHistory();
        String key = "key";
        String val = "val";
        if(list != null && list.size() > 0){
            List<JSONObject> airtempValueList = new ArrayList();
            List<JSONObject> airhumiValueList = new ArrayList();
            List<JSONObject> soiltempList = new ArrayList();
            List<JSONObject> soilhumiList = new ArrayList();
            List<JSONObject> co2List = new ArrayList();
            List<JSONObject> sundataList = new ArrayList();
            for (GreenhouseMonitorData tem : list){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sdf.format(tem.getDatetime());

                JSONObject airtempValue = new JSONObject();
                airtempValue.put(key,dateTime);
                airtempValue.put(val,tem.getAirtemp());
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
                double avgSoilHumi = (tem.getSoilhumi1() + tem.getSoilhumi2() + tem.getSoilhumi3()) / 3;
                JSONObject soilhumi = new JSONObject();
                soilhumi.put("key",dateTime);
                soilhumi.put("val",avgSoilHumi);
                soilhumiList.add(soilhumi);

                JSONObject co2Value = new JSONObject();
                co2Value.put("key",dateTime);
                co2Value.put("val",tem.getCo2());
                co2List.add(co2Value);

                JSONObject sundateValue = new JSONObject();
                sundateValue.put("key",dateTime);
                sundateValue.put("val",tem.getSundata());
                sundataList.add(sundateValue);
            }

            JSONObject airtemp = new JSONObject();
            airtemp.put(key,"空气温度");
            airtemp.put(val,airtempValueList);
            result.add(airtemp);

            JSONObject airhumi = new JSONObject();
            airhumi.put(key,"空气湿度");
            airhumi.put(val,airhumiValueList);
            result.add(airhumi);

            JSONObject soiltemp = new JSONObject();
            soiltemp.put(key,"土壤温度");
            soiltemp.put(val,soiltempList);
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
