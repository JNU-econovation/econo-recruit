package com.econovation.recruitcommon.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimeTraceAspect {
    @Pointcut("@annotation(com.econovation.recruitcommon.annotation.TimeTrace)")
    private void timeTracePointCut() {}

    @Around("timeTracePointCut()")
    public Object timeTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("START: {}", joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finishTime = System.currentTimeMillis();
            long timeMs = finishTime - startTime;
            log.info("END: {} {}ms", joinPoint.toString(), timeMs);
        }
    }
}
