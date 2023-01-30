package com.johnnyb.oktest.config;

import com.johnnyb.oktest.cache.CacheDefinitions;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Bean
    public CacheManager cacheManager(CacheDefinitions cacheDefinitions) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        cacheDefinitions.getCacheConfig().forEach(cacheConfig ->
        {
            caffeineCacheManager.registerCustomCache(cacheConfig.getName(),
                    Caffeine.newBuilder()
                            .maximumSize(cacheConfig.getMaximumSize())
                            .expireAfterWrite(cacheConfig.getExpireAfterWrite(), TimeUnit.SECONDS)
                            .recordStats()
                            .build());
            logger.info("CACHE {}", cacheConfig);
        });
        return caffeineCacheManager;
    }


}