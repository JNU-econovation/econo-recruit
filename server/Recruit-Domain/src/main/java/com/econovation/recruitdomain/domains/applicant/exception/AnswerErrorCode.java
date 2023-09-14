package com.econovation.recruitdomain.domains.applicant.exception;

import static com.econovation.recruitcommon.consts.RecruitStatic.BAD_REQUEST;
import static com.econovation.recruitcommon.consts.RecruitStatic.NOT_FOUND;

import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.BaseErrorCode;
import com.econovation.recruitcommon.exception.ErrorReason;
import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnswerErrorCode implements BaseErrorCode {
    ANSWER_NOT_FOUND(NOT_FOUND, "ANSWER_NOT_FOUND", "지원자를 찾을 수 없습니다."),
    ANSWER_EMPTY_FIELD(BAD_REQUEST, "ANSWER_400_0", "필수 입력값이 없습니다."),
    ANSWER_DUPLICATE_SUBMIT(BAD_REQUEST, "ANSWER_400_1", "이미 지원한 지원자입니다."),
    ;

    private Integer status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getReason();
    }
}
