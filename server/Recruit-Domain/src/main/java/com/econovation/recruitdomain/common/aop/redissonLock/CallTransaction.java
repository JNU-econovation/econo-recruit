package com.econovation.recruitdomain.common.aop.redissonLock;

import org.aspectj.lang.ProceedingJoinPoint;

public interface CallTransaction {
    Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable;
}
