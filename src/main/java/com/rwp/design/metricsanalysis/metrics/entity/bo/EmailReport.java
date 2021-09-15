package com.rwp.design.metricsanalysis.metrics.entity.bo;

import com.rwp.design.metricsanalysis.metrics.entity.abstracts.Report;
import com.rwp.design.metricsanalysis.metrics.entity.to.RequestStat;
import org.springframework.stereotype.Component;

/**
 * @program: metrics-analysis
 * @description:
 * @author: wp.ren
 * @create: 2021-09-15 16:02
 **/
@Component
public class EmailReport implements Report {
    @Override
    public void report(RequestStat requestStat) {

    }
}
