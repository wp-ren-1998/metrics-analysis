package com.rwp.design.metricsanalysis.metrics.entity.bo;

import com.rwp.design.metricsanalysis.metrics.entity.abstracts.MetricsStorage;
import com.rwp.design.metricsanalysis.metrics.entity.abstracts.Report;
import com.rwp.design.metricsanalysis.metrics.entity.to.RequestInfo;
import com.rwp.design.metricsanalysis.metrics.entity.to.RequestStat;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @program: metrics-analysis
 * @description: 上下文对象
 * @author: wp.ren
 * @create: 2021-09-13 10:21
 **/
@Component
public class MetricsContext {
    @Resource
    private ScheduledExecutorService executorService;
    @Resource(type = RedisMetricsStorage.class)
    private MetricsStorage metricsStorage;
    @Resource(type = ConsoleReport.class)
    private Report consoleReport;

    /**
     * @param periodInSeconds 周期
     * @param durationInSeconds 统计区间
     */
    public void startRepeatedReportByConsole(long periodInSeconds,long durationInSeconds){
        executorService.scheduleAtFixedRate(() -> {
            long durationInMs = durationInSeconds * 1000;
            long endTimeInMs = System.currentTimeMillis();
            long startTimeInMs = endTimeInMs - durationInMs;
            Map<String, List<RequestInfo>> requestInfo = metricsStorage.getRequestInfo(startTimeInMs, endTimeInMs);
            requestInfo.values().stream()
                    .map(requestInfos -> Aggregator.aggregate(requestInfos, startTimeInMs, endTimeInMs))
                    .collect(Collectors.toList())
                    .forEach(item -> consoleReport.report(item));
        },0,periodInSeconds, TimeUnit.SECONDS);
    }

    public RequestStat aggRequestStat(String apiName,long startTimeInMs,long endTimeInMs){
        List<RequestInfo> requestInfos = metricsStorage.getRequestInfo(apiName, startTimeInMs, endTimeInMs);
        return Aggregator.aggregate(requestInfos, startTimeInMs, endTimeInMs);
    }



}
