package com.johnnyb.oktest.cache;

import com.johnnyb.oktest.util.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "cache.definitions")
@PropertySource(value = "classpath:caches.yml", factory = YamlPropertySourceFactory.class)
@Configuration
public class CacheDefinitions {
    private List<CacheConfig> cacheConfig;

}
