package com.douzi.greenhouse_system.config.log;

import lombok.Data;



@Data
public class OperLogVo {

    private String ip;
    private String url;
    private String methodName;
    private String operModul; //操作模块
    private String operType; //操作类型
    private String operDesc; //操作说明



}
