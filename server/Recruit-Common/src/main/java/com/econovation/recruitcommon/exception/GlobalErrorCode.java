package com.econovation.recruitcommon.exception;

import static com.econovation.recruitcommon.consts.RecruitStatic.*;

import com.econovation.recruitcommon.annotation.ExplainError;
import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 글로벌 관련 예외 코드들이 나온 곳입니다. 인증 , global, aop 종류등 도메인 제외한 exception 코드들이 모이는 곳입니다. 도메인 관련 Exception
 * code 들은 도메인 내부 exception 패키지에 위치시키면 됩니다.
 */
@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
    @ExplainError("백엔드에서 예시로만든 에러입니다. 개발용!이에유! 신경쓰지마세유")
    EXAMPLE_NOT_FOUND(NOT_FOUND, "EXAMPLE_404_1", "예시를 찾을 수 없는 오류입니다."),

    @ExplainError("밸리데이션 (검증 과정 수행속 ) 발생하는 오류입니다.")
    ARGUMENT_NOT_VALID_ERROR(BAD_REQUEST, "GLOBAL_400_1", "검증 오류"),

    @ExplainError("accessToken 만료시 발생하는 오류입니다.")
    TOKEN_EXPIRED(UNAUTHORIZED, "AUTH_401_1", "인증 시간이 만료되었습니다. 인증토큰을 재 발급 해주세요"),

    @ExplainError("refreshToken 만료시 발생하는 오류입니다.")
    REFRESH_TOKEN_EXPIRED(FORBIDDEN, "AUTH_403_1", "인증 시간이 만료되었습니다. 재 로그인 해주세요."),
    @ExplainError("헤더에 올바른 accessToken을 담지않았을 때 발생하는 오류(형식 불일치 등)")
    ACCESS_TOKEN_NOT_EXIST(FORBIDDEN, "AUTH_403_2", "알맞은 accessToken을 넣어주세요."),
    @ExplainError("인증 토큰이 잘못됐을 때 발생하는 오류입니다.")
    INVALID_TOKEN(UNAUTHORIZED, "GLOBAL_401_1", "잘못된 토큰입니다. 재 로그인 해주세요"),

    @ExplainError("500번대 알수없는 오류입니다. 서버 관리자에게 문의 주세요")
    INTERNAL_SERVER_ERROR(INTERNAL_SERVER, "GLOBAL_500_1", "서버 오류. 관리자에게 문의 부탁드립니다."),
    @ExplainError("XSS 공격이 의심되는 입력이 감지되었습니다. 정상적인 입력값을 넣어주세요.")
    XSS_SCRIPT_ATTACK(BAD_REQUEST, "GLOBAL_400_3", "XSS 공격이 의심되는 입력이 감지되었습니다. 정상적인 입력값을 넣어주세요."),

    OTHER_SERVER_BAD_REQUEST(BAD_REQUEST, "FEIGN_400_1", "Other server bad request"),
    OTHER_SERVER_UNAUTHORIZED(BAD_REQUEST, "FEIGN_400_2", "Other server unauthorized"),
    OTHER_SERVER_FORBIDDEN(BAD_REQUEST, "FEIGN_400_3", "Other server forbidden"),
    OTHER_SERVER_EXPIRED_TOKEN(BAD_REQUEST, "FEIGN_400_4", "Other server expired token"),
    OTHER_SERVER_NOT_FOUND(BAD_REQUEST, "FEIGN_400_5", "Other server not found error"),
    OTHER_SERVER_INTERNAL_SERVER_ERROR(
            BAD_REQUEST, "FEIGN_400_6", "Other server internal server error"),
    NOT_AVAILABLE_REDISSON_LOCK(INTERNAL_SERVER, "Redisson_500_1", "can not get redisson lock"),
    SECURITY_CONTEXT_NOT_FOUND(INTERNAL_SERVER, "GLOBAL_500_2", "security context not found"),
    BAD_LOCK_IDENTIFIER(INTERNAL_SERVER, "AOP_500_1", "락의 키값이 잘못 세팅 되었습니다"),
    BAD_FILE_EXTENSION(BAD_REQUEST, "FILE_400_1", "파일 확장자가 잘못 되었습니다."),
    OUT_OF_INDEX(BAD_REQUEST, "GLOBAL_400_2", "인덱스 범위를 벗어났습니다."),
    TOO_MANY_REQUEST(TOO_MANY_REQUESTS, "GLOBAL_429_1", "과도한 요청을 보내셨습니다. 잠시 기다려 주세요."),
    INVALID_PASSWORD(BAD_REQUEST, "GLOBAL_400_4", "비밀번호는 10자 이상 숫자, 특수문자를 포함해야 합니다."),
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
