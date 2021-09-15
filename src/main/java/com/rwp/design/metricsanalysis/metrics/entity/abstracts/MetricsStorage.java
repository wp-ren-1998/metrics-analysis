package com.rwp.design.metricsanalysis.metrics.entity.abstracts;

import com.rwp.design.metricsanalysis.metrics.entity.to.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * @program: metrics-analysis
 * @description: 数据存储
 * @author: wp.ren
 * @create: 2021-09-13 10:21
 **/
public interface MetricsStorage {
    void saveRequestInfo(RequestInfo requestInfo);
    List<RequestInfo> getRequestInfo(String apiName, Long startTimeInMillis, Long endTimeInMillis);
    Map<String,List<RequestInfo>> getRequestInfo(Long startTimeInMillis, Long endTimeInMillis);
}
