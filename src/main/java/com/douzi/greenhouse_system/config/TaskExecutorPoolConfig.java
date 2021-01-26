package com.douzi.greenhouse_system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池
 */

@Configuration
public class TaskExecutorPoolConfig implements AsyncConfigurer {


    //核心线程数：线程池创建时候初始化的线程数
    private static final int CORE_POOL_SIZE = 3;
    //线程池最大线程数: 线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
    private static final int MAX_POOL_SIZE = 10;
    //允许线程的空闲时间(单位：秒)：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
    private static final int KEEP_ALIVE_TIME = 60;
    //缓冲队列：用来缓冲执行任务的队列
    private static final int QUEUE_CAPACITY = 10;
    /**
     * 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
     */
    private String threadNamePrefix = "banyechan_";
    /**
     * 通过重写getAsyncExecutor方法，制定默认的任务执行由该方法产生
     *
     * 配置类实现AsyncConfigurer接口并重写getAsyncExcutor方法，并返回一个ThreadPoolTaskExevutor
     * 这样我们就获得了一个基于线程池的TaskExecutor
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        // 线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }


}
