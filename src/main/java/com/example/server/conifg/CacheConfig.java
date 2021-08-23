package com.example.server.conifg;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/7/8 16:30
 */
public class CacheConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    @Value("${spring.cache.cache-name")
    private String cacheName;

    private static final long EXPIRED_AFTER_ACCESS = 60;

    private static final long EXPIRED_AFTER_WRITE = 120;

    private static final int INITIAL_CAPACITY = 100;

    private static final int MAX_CAPACITY = 1000;

    @Bean(name = "caffine")
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffine = Caffeine.newBuilder()
                .initialCapacity(INITIAL_CAPACITY)
                .maximumSize(MAX_CAPACITY)
                .expireAfterAccess(EXPIRED_AFTER_ACCESS, TimeUnit.SECONDS)
                .expireAfterWrite(EXPIRED_AFTER_WRITE, TimeUnit.SECONDS);
        cacheManager.setCaffeine(caffine);
        cacheManager.setCacheNames(Collections.singletonList(cacheName));
        return cacheManager;
    }
}
