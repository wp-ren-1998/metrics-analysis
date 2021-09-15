package com.rwp.design.metricsanalysis.metrics.entity.abstracts;

import com.rwp.design.metricsanalysis.metrics.entity.to.RequestStat;

/**
 * @program: metrics-analysis
 * @description: 通知
 * @author: wp.ren
 * @create: 2021-09-13 10:21
 **/
public interface Report {
    void report(RequestStat requestStat);
}
