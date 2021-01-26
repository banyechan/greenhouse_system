package com.douzi.greenhouse_system.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SystemCache  implements CommandLineRunner {


    //系统参数缓存
    // 注意： 必须在声明时,就要初始化。如若在方法中再初始化,则在系统其他地方引用时,则为null
    public static Map<String,Object> cacheMap = new HashMap<String, Object>();


    @Override
    public void run(String... args) throws Exception {
        log.info("------------  初始化缓存  --------------");
        //cacheMap = new HashMap<String, Object>();  //不要在此处重新初始化,否则在系统其他地方引用时,则为null
        cacheMap.put("1","张三");
        cacheMap.put("2","李斯");
        cacheMap.put("3","王武");

        System.out.println("=========== cacheMap ==="  + cacheMap);

        log.info("-------------    初始化缓存结束     ------------");

    }






}
