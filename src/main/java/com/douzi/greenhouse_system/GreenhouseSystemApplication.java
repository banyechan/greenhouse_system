package com.douzi.greenhouse_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 项目启动类
 */

@SpringBootApplication
@EnableAsync
public class GreenhouseSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenhouseSystemApplication.class, args);
    }

}
