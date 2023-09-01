package com.econovation.recruitdomain.domains.interviewer.exception;

import static com.econovation.recruitcommon.consts.RecruitStatic.*;

import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.BaseErrorCode;
import com.econovation.recruitcommon.exception.ErrorReason;
import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InterviewerErrorCode implements BaseErrorCode {
    INTERVIEWER_DUPLICATE_CREATED(BAD_REQUEST, "INTERVIEWER_400_1", "중복된 면접관을 생성했습니다."),
    INTERVIEWER_NOT_FOUND(NOT_FOUND, "INTERVIEWER_404_1", "찾을 수 없는 면접관입니다."),
    INTERVIEWER_NOT_ONE_TYPE(BAD_REQUEST, "INTERVIEWER_400_1", "Role 이 한 종류가 아닙니다."),
    INTERVIEWER_NOT_LOGIN(UNAUTHORIZED, "INTERVIEWER_400_2", "로그인이 되지 않은 면접관입니다."),
    INTERVIEWER_IDP_SERVER(FORBIDDEN, "INTERVIEWER_400_3", "Idp가 정상 동작하지 않습니다."),
    INTERVIEWER_INVALID_ROLE(BAD_REQUEST, "INTERVIEWER_400_4", "유효하지 않은 Role 을 입력했습니다."),
    INVALID_PASSWORD(BAD_REQUEST, "INTERVIEWER_400_5", "유효하지 않은 비밀번호입니다."),
    INTERVIEWER_NOT_MATCH(NOT_FOUND, "INTERVIEWER_400_6", "등록되지 않은 이메일과 비밀번호로 로그인을 시도했습니다."),
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
