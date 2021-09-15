package com.rwp.design.metricsanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class MetricsAnalysisApplication {

    public static void main(String[] args){
        SpringApplication.run(MetricsAnalysisApplication.class, args);
    }

}
