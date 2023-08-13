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
public enum BoardErrorCode implements BaseErrorCode {
    BOARD_INVALID_LOCATION(BAD_REQUEST, "BOARD_400_1", "유효하지 않은 위치입니다."),
    INVALID_HOPE_FIELD(BAD_REQUEST, "BOARD_400_2", "유효하지 않은 희망 분야입니다."),
    BOARD_NOT_FOUND(NOT_FOUND, "BOARD_400_3", "보드를 찾을 수 없습니다."),
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
