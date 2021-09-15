package com.rwp.design.metricsanalysis.metrics.entity.bo;

import com.google.gson.Gson;
import com.rwp.design.metricsanalysis.metrics.entity.abstracts.Report;
import com.rwp.design.metricsanalysis.metrics.entity.to.RequestStat;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: metrics-analysis
 * @description: console
 * @author: wp.ren
 * @create: 2021-09-15 14:45
 **/
@Component
public class ConsoleReport implements Report {
    @Resource
    private Gson gson;

    @Override
    public void report(RequestStat requestStat) {
        System.out.println(gson.toJson(requestStat));
    }
}
