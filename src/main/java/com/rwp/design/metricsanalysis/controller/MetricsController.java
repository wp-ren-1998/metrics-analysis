package com.rwp.design.metricsanalysis.controller;

import com.rwp.design.metricsanalysis.metrics.entity.anno.Metrics;
import com.rwp.design.metricsanalysis.metrics.entity.bo.MetricsContext;
import com.rwp.design.metricsanalysis.metrics.entity.to.RequestStat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: metrics-analysis
 * @description:
 * @author: wp.ren
 * @create: 2021-09-15 16:03
 **/
@RestController
public class MetricsController {
    @Resource
    private MetricsContext context;
    @Metrics
    @GetMapping("/metrics")
    public RequestStat metrics(@RequestParam String apiName,
                               @RequestParam Long startTimeInMs,
                               @RequestParam Long endTimeInMs){
        return context.aggRequestStat(apiName,startTimeInMs,endTimeInMs);
    }
}
