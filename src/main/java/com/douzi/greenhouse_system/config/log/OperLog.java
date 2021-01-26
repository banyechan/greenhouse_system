package com.douzi.greenhouse_system.config.log;

import java.lang.annotation.*;

/**
 * 自定义操作日志注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {

    String operModul() default ""; //操作模块
    String operType() default ""; //操作类型
    String operDesc() default ""; //操作说明

}
