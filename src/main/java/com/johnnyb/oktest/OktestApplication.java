package com.johnnyb.oktest;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class OktestApplication {

    @Autowired
    CacheManager cacheManager;
    private final Logger logger = LoggerFactory.getLogger(OktestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OktestApplication.class, args);
    }

    @Scheduled(fixedRate = 60000)
    public void writeStatus() {
        try {
            cacheManager.getCacheNames().forEach(
                    name -> {
                        Cache cache = cacheManager.getCache(name);
                        com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = ((CaffeineCache) cache).getNativeCache();
                        CacheStats stats = nativeCache.stats();
                        logger.info("CACHE STATS [{}]: {} ", name, stats);
                    }
            );
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
