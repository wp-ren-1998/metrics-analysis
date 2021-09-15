package com.rwp.design.metricsanalysis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @program: metrics-analysis
 * @description:
 * @author: wp.ren
 * @create: 2021-09-15 15:10
 **/
@Configuration
public class ScheduledExecutorConfig {
    @Bean
    public ScheduledExecutorService getScheduledExecutorService(){
        return new ScheduledThreadPoolExecutor(3);
    }
}
