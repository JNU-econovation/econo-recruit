package com.econovation.recruitdomain.domains.score.exception;

import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.BaseErrorCode;
import com.econovation.recruitcommon.exception.ErrorReason;
import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.econovation.recruitcommon.consts.RecruitStatic.BAD_REQUEST;
import static com.econovation.recruitcommon.consts.RecruitStatic.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ScoreErrorCode implements BaseErrorCode {
    SCORE_NOT_FOUND(NOT_FOUND, "LABEL_404_1", "해당 라벨을 찾을 수 없습니다."),
    SCORE_INVALID_FIELD_SUBMIT(BAD_REQUEST, "LABEL_400_1", "유효하지 않은 평가 항목에 대한 점수입니다."),
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
