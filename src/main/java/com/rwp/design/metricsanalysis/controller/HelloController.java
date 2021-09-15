package com.rwp.design.metricsanalysis.controller;

import com.rwp.design.metricsanalysis.metrics.entity.anno.Metrics;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: metrics-analysis
 * @description: TestMetrics
 * @author: wp.ren
 * @create: 2021-09-13 11:18
 **/
@RestController
public class HelloController {
    @Metrics
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

}
