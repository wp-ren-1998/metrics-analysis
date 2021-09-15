package com.rwp.design.metricsanalysis.metrics.entity.anno;

import java.lang.annotation.*;

@Documented
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Metrics {

}
