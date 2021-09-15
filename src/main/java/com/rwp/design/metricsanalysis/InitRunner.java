package com.rwp.design.metricsanalysis;

import com.rwp.design.metricsanalysis.metrics.entity.bo.MetricsContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: metrics-analysis
 * @description:
 * @author: wp.ren
 * @create: 2021-09-15 15:33
 **/
@Component
@Order(2)
public class InitRunner implements CommandLineRunner {
    @Resource
    private MetricsContext context;
    @Override
    public void run(String... args) throws Exception {
        context.startRepeatedReportByConsole(5,5);
    }
}
