package com.econovation.recruit.config;

import com.econovation.recruit.config.security.SecurityUtils;
import com.econovation.recruitcommon.exception.BaseErrorCode;
import com.econovation.recruitcommon.exception.ErrorReason;
import com.econovation.recruitcommon.exception.ErrorResponse;
import com.econovation.recruitcommon.exception.GlobalErrorCode;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.exception.RecruitDynamicException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class SwaggerConfig extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String url =
                UriComponentsBuilder.fromHttpRequest(
                                new ServletServerHttpRequest(servletWebRequest.getRequest()))
                        .build()
                        .toUriString();

        ErrorResponse errorResponse =
                new ErrorResponse(status.value(), status.name(), ex.getMessage(), url);
        return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        String url =
                UriComponentsBuilder.fromHttpRequest(
                                new ServletServerHttpRequest(servletWebRequest.getRequest()))
                        .build()
                        .toUriString();
        Map<String, Object> fieldAndErrorMessages =
                errors.stream()
                        .collect(
                                Collectors.toMap(
                                        FieldError::getField, FieldError::getDefaultMessage));

        String errorsToJsonString = new ObjectMapper().writeValueAsString(fieldAndErrorMessages);
        ErrorResponse errorResponse =
                new ErrorResponse(status.value(), status.name(), errorsToJsonString, url);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(RecruitCodeException.class)
    public ResponseEntity<ErrorResponse> RecruitCodeExceptionHandler(
            RecruitCodeException e, HttpServletRequest request) {
        BaseErrorCode code = e.getErrorCode();
        ErrorReason errorReason = code.getErrorReason();
        ErrorResponse errorResponse =
                new ErrorResponse(errorReason, request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.valueOf(errorReason.getStatus()))
                .body(errorResponse);
    }

    /** Request Param Validation 예외 처리 */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> ConstraintViolationExceptionHandler(
            ConstraintViolationException e, HttpServletRequest request) {
        Map<String, Object> bindingErrors = new HashMap<>();
        e.getConstraintViolations()
                .forEach(
                        constraintViolation -> {
                            List<String> propertyPath =
                                    List.of(
                                            constraintViolation
                                                    .getPropertyPath()
                                                    .toString()
                                                    .split("\\."));
                            String path =
                                    propertyPath.stream()
                                            .skip(propertyPath.size() - 1L)
                                            .findFirst()
                                            .orElse(null);
                            bindingErrors.put(path, constraintViolation.getMessage());
                        });

        ErrorReason errorReason =
                ErrorReason.builder()
                        .code("BAD_REQUEST")
                        .status(400)
                        .reason(bindingErrors.toString())
                        .build();
        ErrorResponse errorResponse =
                new ErrorResponse(errorReason, request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.valueOf(errorReason.getStatus()))
                .body(errorResponse);
    }

    @ExceptionHandler(RecruitDynamicException.class)
    public ResponseEntity<ErrorResponse> RecruitDynamicExceptionHandler(
            RecruitDynamicException e, HttpServletRequest request) {
        ErrorResponse errorResponse =
                new ErrorResponse(
                        e.getStatus(),
                        e.getCode(),
                        e.getReason(),
                        request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request)
            throws IOException {
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final Long userId = SecurityUtils.getCurrentUserId();
        String url =
                UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(request))
                        .build()
                        .toUriString();

        log.error("INTERNAL_SERVER_ERROR", e);
        GlobalErrorCode internalServerError = GlobalErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse =
                new ErrorResponse(
                        internalServerError.getStatus(),
                        internalServerError.getCode(),
                        internalServerError.getReason(),
                        url);

        //        slackInternalErrorSender.execute(cachingRequest, e, userId);
        return ResponseEntity.status(HttpStatus.valueOf(internalServerError.getStatus()))
                .body(errorResponse);
    }
}
