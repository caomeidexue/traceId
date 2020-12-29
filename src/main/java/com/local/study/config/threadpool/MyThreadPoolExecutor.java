package com.local.study.config.threadpool;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/9 3:53 下午
 */
@Configuration
@EnableAsync
public class MyThreadPoolExecutor{

    @Bean("formTaskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(16);
        taskExecutor.setQueueCapacity(50);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//配置拒绝策略
        taskExecutor.setThreadNamePrefix("Forum-AsyncExecutor");
        taskExecutor.initialize();
        return taskExecutor;
    }


}
