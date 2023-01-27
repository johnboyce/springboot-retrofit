package com.comcast.oktest.config;

import com.comcast.oktest.cache.CacheDefinitions;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@EnableConfigurationProperties(value = CacheDefinitions.class)
public class CacheConfig {
    private final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

    @Autowired
    private CacheDefinitions cacheDefinitions;

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder().recordStats().initialCapacity(10);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine, CacheDefinitions cacheDefinitions) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        cacheDefinitions.getCacheConfig().forEach(cacheConfig ->
        {
            caffeineCacheManager.registerCustomCache(cacheConfig.getName(),
                    Caffeine.newBuilder()
                            .maximumSize(cacheConfig.getMaximumSize())
                            .expireAfterWrite(cacheConfig.getExpireAfterWrite(), TimeUnit.SECONDS)
                            .build());
            logger.info("CACHE {}", cacheConfig);
        });
        return caffeineCacheManager;
    }
}