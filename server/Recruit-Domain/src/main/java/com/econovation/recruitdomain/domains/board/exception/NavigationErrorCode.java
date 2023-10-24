package com.econovation.recruitdomain.domains.board.exception;

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
public enum NavigationErrorCode implements BaseErrorCode {
    NAVIGATION_NOT_FOUND(NOT_FOUND, "NAVIGATION_404_1", "해당 navigation을 찾을 수 없습니다."),
    NAVIGATION_DUPLICATE_CREATED(BAD_REQUEST, "NAVIGATION_404_2", "중복된 navigation을 생성했습니다.");

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
