package com.rwp.design.metricsanalysis.metrics.entity.to;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @program: metrics-analysis
 * @description: responseVo
 * @author: wp.ren
 * @create: 2021-09-13 10:21
 **/
public class RequestStat implements Serializable {

    private static final long serialVersionUID = -3474851406840323855L;
    private static final RequestStat EMPTY = new RequestStat();

    private String apiName = "default";
    private LocalDateTime startTime = null;
    private LocalDateTime endTime = null;
    private Double maxResponseTime = Double.MAX_VALUE;
    private Double minResponseTime = Double.MIN_VALUE;
    private Double avgResponseTime = -1D;
    private Double p999ResponseTime = -1D;
    private Double p99ResponseTime = -1D;
    private Long count = 0L;
    private Long tps = 0L;

    private RequestStat() {
    }

    private RequestStat(Builder builder) {
        this.apiName = builder.apiName;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.maxResponseTime = builder.maxResponseTime;
        this.minResponseTime = builder.minResponseTime;
        this.avgResponseTime = builder.avgResponseTime;
        this.p999ResponseTime = builder.p999ResponseTime;
        this.p99ResponseTime = builder.p99ResponseTime;
        this.count = builder.count;
        this.tps = builder.tps;
    }

    public static RequestStat buildEmptyRequestStat() {
        return EMPTY;
    }

    public String getApiName() {
        return apiName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Double getMaxResponseTime() {
        return maxResponseTime;
    }

    public Double getMinResponseTime() {
        return minResponseTime;
    }

    public Double getAvgResponseTime() {
        return avgResponseTime;
    }

    public Double getP999ResponseTime() {
        return p999ResponseTime;
    }

    public Double getP99ResponseTime() {
        return p99ResponseTime;
    }

    public Long getCount() {
        return count;
    }

    public Long getTps() {
        return tps;
    }

    public static final class Builder {
        private final String apiName;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Double maxResponseTime;
        private Double minResponseTime;
        private Double avgResponseTime;
        private Double p999ResponseTime;
        private Double p99ResponseTime;
        private Long count;
        private Long tps;

        public Builder(String apiName) {
            this.apiName = apiName;
        }

        public Builder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder maxResponseTime(Double maxResponseTime) {
            this.maxResponseTime = maxResponseTime;
            return this;
        }

        public Builder minResponseTime(Double minResponseTime) {
            this.minResponseTime = minResponseTime;
            return this;
        }

        public Builder avgResponseTime(Double avgResponseTime) {
            this.avgResponseTime = avgResponseTime;
            return this;
        }

        public Builder p999ResponseTime(Double p999ResponseTime) {
            this.p999ResponseTime = p999ResponseTime;
            return this;
        }

        public Builder p99ResponseTime(Double p99ResponseTime) {
            this.p99ResponseTime = p99ResponseTime;
            return this;
        }

        public Builder count(Long count) {
            this.count = count;
            return this;
        }

        public Builder tps(Long tps) {
            this.tps = tps;
            return this;
        }

        public RequestStat build() {
            return new RequestStat(this);
        }

    }
}
