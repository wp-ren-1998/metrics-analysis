package com.rwp.design.metricsanalysis.config;

import com.rwp.design.metricsanalysis.config.properties.RedisConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program: metrics-analysis
 * @description: redisConfig
 * @author: wp.ren
 * @create: 2021-09-13 11:29
 **/
@Configuration
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public JedisPool redisPoolFactory(RedisConfigProperties properties) {
        logger.info("JedisPool start import");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(properties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(properties.getMaxIdle());
        jedisPoolConfig.setMinIdle(properties.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(properties.getMaxWait());
        jedisPoolConfig.setTestOnBorrow(properties.isTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(properties.isTestOnReturn());
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, properties.getHost(), properties.getPort(), properties.getTimeout());
        logger.info("JedisPool import success!");
        logger.info("redis address：" + properties.getHost() + ":" + properties.getPort());
        return jedisPool;
    }
}
