package com.rwp.design.metricsanalysis.metrics.entity.bo;

import com.rwp.design.metricsanalysis.metrics.entity.to.RequestInfo;
import com.rwp.design.metricsanalysis.metrics.entity.to.RequestStat;
import org.apache.commons.collections.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;

/**
 * @program: metrics-analysis
 * @description: 聚合统计工具类
 * @author: wp.ren
 * @create: 2021-09-13 10:21
 **/
public final class Aggregator {
    private Aggregator() {
    }

    public static RequestStat aggregate(List<RequestInfo> requestInfos, long startTimeInMs, long endTimeInMs) {
        if(CollectionUtils.isEmpty(requestInfos)){
            return RequestStat.buildEmptyRequestStat();
        }
        /**
         * initialize
         */
        Long maxResponseTimeInMs = Long.MIN_VALUE;
        Long minResponseTimeInMs = Long.MAX_VALUE;
        Double avgResponseTime = -1D;
        Double p999ResponseTime = -1D;
        Double p99ResponseTime = -1D;
        Long count = 0L;
        Long tps = 0L;
        /**
         * aggregate
         */
        count = (long) requestInfos.size();
        long sumResponseTimeInMs = 0;
        for (RequestInfo requestInfo : requestInfos) {
            sumResponseTimeInMs += requestInfo.getResponseTime();
            if (requestInfo.getResponseTime() > maxResponseTimeInMs)
                maxResponseTimeInMs = requestInfo.getResponseTime();
            if (requestInfo.getResponseTime() < minResponseTimeInMs)
                minResponseTimeInMs = requestInfo.getResponseTime();
        }
        avgResponseTime = (sumResponseTimeInMs / 1000D) / count;
        long durationInMs = startTimeInMs - endTimeInMs;
        tps = count / durationInMs * 1000;
        requestInfos.sort(Comparator.comparing(RequestInfo::getResponseTime));
        int idx999 = (int) (count * 0.999);
        int idx99 = (int) (count * 0.99);
        p999ResponseTime = requestInfos.get(idx999).getResponseTime() / 1000D;
        p99ResponseTime = requestInfos.get(idx99).getResponseTime() / 1000D;
        /**
         * response
         */
        return new RequestStat.Builder(requestInfos.get(0).getApiName())
                .startTime(LocalDateTime.ofEpochSecond(startTimeInMs/1000, 0, ZoneOffset.ofHours(8)))
                .endTime(LocalDateTime.ofEpochSecond(endTimeInMs/1000, 0, ZoneOffset.ofHours(8)))
                .maxResponseTime(maxResponseTimeInMs / 1000D)
                .minResponseTime(minResponseTimeInMs / 1000D)
                .avgResponseTime(avgResponseTime)
                .p999ResponseTime(p999ResponseTime)
                .p99ResponseTime(p99ResponseTime)
                .count(count)
                .tps(tps)
                .build();
    }
}
