package com.econovation.recruitcommon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecruitDynamicException extends RuntimeException {
    private final int status;
    private final String code;
    private final String reason;
}
