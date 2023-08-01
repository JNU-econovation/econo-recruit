package com.econovation.recruitcommon.exception;

public class NotAvailableRedissonLockException extends RecruitCodeException {

    public static final RecruitCodeException EXCEPTION = new NotAvailableRedissonLockException();

    private NotAvailableRedissonLockException() {
        super(GlobalErrorCode.NOT_AVAILABLE_REDISSON_LOCK);
    }
}
