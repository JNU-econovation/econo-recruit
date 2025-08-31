package com.econovation.recruitdomain.domains.recruitment.exception;

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
public enum RecruitmentErrorCode implements BaseErrorCode {
    RECRUITMENT_NOT_FOUND(NOT_FOUND, "RECRUITMENT_404_1", "해당 모집을 찾을 수 없습니다."),
    RECRUITMENT_ALREADY_EXISTS(BAD_REQUEST, "RECRUITMENT_400_1", "이미 예약된 모집이 존재합니다."),
    RECRUITMENT_INVALID_DATE_1(BAD_REQUEST, "RECRUITMENT_400_2", "모집 시작일은 모집 종료일보다 이후일 수 없습니다."),
    RECRUITMENT_INVALID_DATE_2(BAD_REQUEST, "RECRUITMENT_400_3", "모집 시작일은 현재일보다 이전일 수 없습니다.");

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
