package com.econovation.recruit.api.recruitment.quartz.exception;

public class QuartzException extends RuntimeException {

    public QuartzException(Throwable cause) {
        super("예상치 못한 오류가 발생하였습니다.", cause);
    }
}
