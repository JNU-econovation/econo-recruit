package com.econovation.recruitdomain.config;

import lombok.RequiredArgsConstructor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableAsync
@Configuration
@RequiredArgsConstructor
public class EnableAsyncConfig implements AsyncConfigurer {

    private final CustomAsyncExceptionHandler customAsyncExceptionHandler;

    @Override
    public Executor getAsyncExecutor() {
        return Executors.newThreadPerTaskExecutor(
                Thread.ofVirtual()
                        .name("async-virtual-thread-", 0)
                        .factory()
        );
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return customAsyncExceptionHandler;
    }
}
