package com.econovation.recruitinfrastructure.redis;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.RedisSerializer;

@EnableRedisRepositories(
        basePackages = "com.econovation",
        enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig =
                new RedisStandaloneConfiguration(
                        redisProperties.getHost(), redisProperties.getPort());

        //        if (redisPassword != null && !redisPassword.isBlank())
        redisConfig.setPassword(redisProperties.getPassword());

        LettuceClientConfiguration clientConfig =
                LettuceClientConfiguration.builder()
                        .commandTimeout(Duration.ofSeconds(1))
                        .shutdownTimeout(Duration.ZERO)
                        .build();
        return new LettuceConnectionFactory(redisConfig, clientConfig);
    }

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return RedisSerializer.json(); // 기본값은 JdkSerializationRedisSerializer
    }
}
