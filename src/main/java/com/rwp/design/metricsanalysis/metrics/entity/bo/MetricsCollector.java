package com.rwp.design.metricsanalysis.metrics.entity.bo;

import com.google.gson.Gson;
import com.rwp.design.metricsanalysis.metrics.entity.abstracts.MetricsStorage;
import com.rwp.design.metricsanalysis.metrics.entity.to.RequestInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @program: metrics-analysis
 * @description: 数据采集对象
 * @author: wp.ren
 * @create: 2021-09-13 10:21
 **/
@Component
@Aspect
public class MetricsCollector {
    private static final Logger logger = LoggerFactory.getLogger(MetricsCollector.class);
    @Resource(type = RedisMetricsStorage.class)
    private MetricsStorage metricsStorage;
    @Resource
    private Gson gson;

    @Pointcut(value = "@annotation(com.rwp.design.metricsanalysis.metrics.entity.anno.Metrics)")
    public void MetricsPointCut() {
    }

    @Around("MetricsPointCut()")
    public Object metricsAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String apiName = signature.getMethod().getName();
        long timestamp = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long finishTimestamp = System.currentTimeMillis();
        RequestInfo requestData = new RequestInfo.Builder(apiName)
                .responseTime((finishTimestamp - timestamp))
                .timestamp(timestamp)
                .build();
        try {
            metricsStorage.saveRequestInfo(requestData);
        } catch (Exception all) {
            logger.debug("[[method = metricsAround]] saveRequestInfo error ,data = {}", gson.toJson(requestData));
        }
        return result;
    }
}
