package com.econovation.recruit.api.config;

import com.econovation.recruitcommon.exception.BaseErrorCode;
import com.econovation.recruitcommon.exception.ErrorReason;
import com.econovation.recruitcommon.exception.GlobalErrorCode;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {

        // 1. RecruitCodeException 처리 (비즈니스 예외)
        if (ex instanceof RecruitCodeException recruitCodeException) {
            return handleRecruitCodeException(recruitCodeException, env);
        }

        // 2. 이외의 예외는 내부 서버 오류로 처리
        return handleInternalServerError(ex, env);
    }

    private GraphQLError handleRecruitCodeException(
            RecruitCodeException ex, DataFetchingEnvironment env) {
        BaseErrorCode errorCode = ex.getErrorCode();
        ErrorReason errorReason = errorCode.getErrorReason();

        return GraphqlErrorBuilder.newError()
                .message(errorReason.getReason())
                .errorType(ErrorType.DataFetchingException)
                .location(env.getField().getSourceLocation())
                .path(env.getExecutionStepInfo().getPath())
                .extensions(
                        Map.of(
                                "code", errorReason.getCode(),
                                "status", errorReason.getStatus(),
                                "timestamp",
                                        LocalDateTime.now()
                                                .format(
                                                        DateTimeFormatter.ofPattern(
                                                                "yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))))
                .build();
    }

    private GraphQLError handleInternalServerError(Throwable ex, DataFetchingEnvironment env) {

        GlobalErrorCode internalServerError = GlobalErrorCode.INTERNAL_SERVER_ERROR;

        return GraphqlErrorBuilder.newError()
                .message(internalServerError.getReason())
                .errorType(ErrorType.DataFetchingException)
                .location(env.getField().getSourceLocation())
                .path(env.getExecutionStepInfo().getPath())
                .extensions(
                        Map.of(
                                "code", internalServerError.getCode(),
                                "status", internalServerError.getStatus(),
                                "timestamp",
                                        LocalDateTime.now()
                                                .format(
                                                        DateTimeFormatter.ofPattern(
                                                                "yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))))
                .build();
    }
}
