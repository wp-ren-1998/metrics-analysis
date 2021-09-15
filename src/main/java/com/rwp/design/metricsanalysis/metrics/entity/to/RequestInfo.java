package com.rwp.design.metricsanalysis.metrics.entity.to;

import java.io.Serializable;

/**
 * @program: metrics-analysis
 * @description: 采集数据
 * @author: wp.ren
 * @create: 2021-09-13 10:21
 **/
public class RequestInfo implements Serializable {

    private static final long serialVersionUID = -7794318954580069992L;

    private String apiName;
    private Long responseTime;
    private Long timestamp;

    private RequestInfo() {
    }

    private RequestInfo(Builder builder) {
        this.apiName = builder.apiName;
        this.responseTime = builder.responseTime;
        this.timestamp = builder.timestamp;
    }

    public String getApiName() {
        return apiName;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public static final class Builder {
        private String apiName;
        private Long responseTime;
        private Long timestamp;

        public Builder(String apiName) {
            this.apiName = apiName;
        }

        public Builder responseTime(Long responseTime) {
            this.responseTime = responseTime;
            return this;
        }

        public Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public RequestInfo build() {
            return new RequestInfo(this);
        }
    }
}
