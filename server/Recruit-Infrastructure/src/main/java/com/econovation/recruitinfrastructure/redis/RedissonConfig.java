package com.econovation.recruitinfrastructure.redis;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RedissonConfig {

    private final RedisProperties redisProperties;
    private static final String REDISSON_HOST_PREFIX = "redis://";

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(
                        REDISSON_HOST_PREFIX
                                + redisProperties.getHost()
                                + ":"
                                + redisProperties.getPort());
        return Redisson.create(config);
    }

    /** for bucket4j */
    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        Cache<Object, Object> bucket4j = manager.getCache("bucket4j");
        if (bucket4j == null) {
            manager.createCache("bucket4j", RedissonConfiguration.fromInstance(redissonClient));
        }
        return manager;
    }

    /** for bucket4j */
    @Bean
    ProxyManager<String> proxyManager(CacheManager cacheManager) {
        return new JCacheProxyManager<>(cacheManager.getCache("bucket4j"));
    }
}
