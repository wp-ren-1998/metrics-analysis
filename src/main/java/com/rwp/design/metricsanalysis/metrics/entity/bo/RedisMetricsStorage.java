package com.rwp.design.metricsanalysis.metrics.entity.bo;

import com.google.gson.Gson;
import com.rwp.design.metricsanalysis.metrics.entity.abstracts.MetricsStorage;
import com.rwp.design.metricsanalysis.metrics.entity.to.RequestInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: metrics-analysis
 * @description: RedisStorage
 * @author: wp.ren
 * @create: 2021-09-13 11:20
 **/
@Component
public class RedisMetricsStorage implements MetricsStorage {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private Gson gson;

    private static final String REQUEST_INFO_PREFIX = "request:info:data:";

    /**
     * 存储结构
     * key:REQUEST_INFO_PREFIX + apiName
     * value:Map<timestamp,RequestInfo>
     * Map<appName,Map<timestamp,RequestInfo>>
     *
     * @param requestInfo
     */
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {
        String key = REQUEST_INFO_PREFIX + requestInfo.getApiName();
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(key);
        ops.put(requestInfo.getTimestamp() + "", gson.toJson(requestInfo));
    }

    @Override
    public List<RequestInfo> getRequestInfo(String apiName, Long startTimeInMillis, Long endTimeInMillis) {
        if (Strings.isBlank(apiName)) {
            return new ArrayList<>();
        }
        String key = REQUEST_INFO_PREFIX + apiName;
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(key);

        Set<String> keys = ops.keys();
        Set<String> filterSet = keys.stream()
                .filter(item -> {
                    long timestamp = Long.parseLong(item);
                    return timestamp >= startTimeInMillis && timestamp <= endTimeInMillis;
                }).collect(Collectors.toSet());
        return Objects.requireNonNull(ops.multiGet(filterSet)).stream()
                .map(jsonStr -> gson.fromJson(jsonStr, RequestInfo.class))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfo(Long startTimeInMillis, Long endTimeInMillis) {
        Set<String> hitKeys = stringRedisTemplate.keys(REQUEST_INFO_PREFIX + "*");
        Map<String, List<RequestInfo>> result = new HashMap<>();
        hitKeys.stream().forEach(hitKey -> {
            List<RequestInfo> data = stringRedisTemplate.opsForHash().entries(hitKey).entrySet().stream()
                    .filter(entry -> {
                        long timestamp = Long.parseLong((String) entry.getKey());
                        return timestamp > startTimeInMillis && timestamp < endTimeInMillis;
                    })
                    .map(entry -> gson.fromJson((String) entry.getValue(), RequestInfo.class))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(data)) result.put(data.get(0).getApiName(), data);
        });
        return result;
    }
}
