package com.econovation.recruit.api.config;

import static java.util.stream.Collectors.groupingBy;

import com.econovation.recruitcommon.annotation.ApiErrorCodeExample;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitcommon.annotation.DisableSwaggerSecurity;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.BaseErrorCode;
import com.econovation.recruitcommon.exception.ErrorReason;
import com.econovation.recruitcommon.exception.ErrorResponse;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class SwaggerConfig extends ResponseEntityExceptionHandler {
    private final ApplicationContext applicationContext;

    @Bean
    public OpenAPI openAPI(ServletContext servletContext) {
        String contextPath = servletContext.getContextPath();
        Server server = new Server().url(contextPath);
        return new OpenAPI().servers(List.of(server)).components(authSetting()).info(swaggerInfo());
    }

    private Info swaggerInfo() {
        License license = new License();
        license.setUrl("https://github.com/NU-econovation/econo-recruit");
        license.setName("Econovation-Recruit");

        return new Info()
                .version("v0.0.1")
                .title("\"Econovation Recruit 서버 API문서\"")
                .description("Econovation 신입모집 플랫폼의 API 문서 입니다.")
                .license(license);
    }

    private Components authSetting() {
        return new Components()
                .addSecuritySchemes(
                        "access-token",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization"));
    }

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }
    /*
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

    */
    /** Request Param Validation 예외 처리 */
    /*
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
    }*/
    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            DisableSwaggerSecurity methodAnnotation =
                    handlerMethod.getMethodAnnotation(DisableSwaggerSecurity.class);
            ApiErrorExceptionsExample apiErrorExceptionsExample =
                    handlerMethod.getMethodAnnotation(ApiErrorExceptionsExample.class);
            ApiErrorCodeExample apiErrorCodeExample =
                    handlerMethod.getMethodAnnotation(ApiErrorCodeExample.class);

            List<String> tags = getTags(handlerMethod);
            // DisableSecurity 어노테이션있을시 스웨거 시큐리티 설정 삭제
            if (methodAnnotation != null) {
                operation.setSecurity(Collections.emptyList());
            }
            // 태그 중복 설정시 제일 구체적인 값만 태그로 설정
            if (!tags.isEmpty()) {
                operation.setTags(Collections.singletonList(tags.get(0)));
            }
            // ApiErrorExceptionsExample 어노테이션 단 메소드 적용
            if (apiErrorExceptionsExample != null) {
                generateExceptionResponseExample(operation, apiErrorExceptionsExample.value());
            }
            // ApiErrorCodeExample 어노테이션 단 메소드 적용
            if (apiErrorCodeExample != null) {
                generateErrorCodeResponseExample(operation, apiErrorCodeExample.value());
            }
            return operation;
        };
    }
    /**
     * BaseErrorCode 타입의 이넘값들을 문서화 시킵니다. ExplainError 어노테이션으로 부가설명을 붙일수있습니다. 필드들을 가져와서 예시 에러 객체를
     * 동적으로 생성해서 예시값으로 붙입니다.
     */
    private void generateErrorCodeResponseExample(
            Operation operation, Class<? extends BaseErrorCode> type) {
        ApiResponses responses = operation.getResponses();

        BaseErrorCode[] errorCodes = type.getEnumConstants();

        Map<Integer, List<ExampleHolder>> statusWithExampleHolders =
                Arrays.stream(errorCodes)
                        .map(
                                baseErrorCode -> {
                                    try {
                                        ErrorReason errorReason = baseErrorCode.getErrorReason();
                                        return ExampleHolder.builder()
                                                .holder(
                                                        getSwaggerExample(
                                                                baseErrorCode.getExplainError(),
                                                                errorReason))
                                                .code(errorReason.getStatus())
                                                .name(errorReason.getCode())
                                                .build();
                                    } catch (NoSuchFieldException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                        .collect(groupingBy(ExampleHolder::getCode));

        addExamplesToResponses(responses, statusWithExampleHolders);
    }

    /**
     * SwaggerExampleExceptions 타입의 클래스를 문서화 시킵니다. SwaggerExampleExceptions 타입의 클래스는 필드로
     * RecruitCodeException 타입을 가지며, RecruitCodeException 의 errorReason 와,ExplainError 의 설명을
     * 문서화시킵니다.
     */
    private void generateExceptionResponseExample(Operation operation, Class<?> type) {
        ApiResponses responses = operation.getResponses();

        // ----------------생성
        Object bean = applicationContext.getBean(type);
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        Map<Integer, List<ExampleHolder>> statusWithExampleHolders =
                Arrays.stream(declaredFields)
                        .filter(field -> field.getAnnotation(ExplainError.class) != null)
                        .filter(field -> field.getType() == RecruitCodeException.class)
                        .map(
                                field -> {
                                    try {
                                        RecruitCodeException exception =
                                                (RecruitCodeException) field.get(bean);
                                        ExplainError annotation =
                                                field.getAnnotation(ExplainError.class);
                                        String value = annotation.value();
                                        ErrorReason errorReason = exception.getErrorReason();
                                        return ExampleHolder.builder()
                                                .holder(getSwaggerExample(value, errorReason))
                                                .code(errorReason.getStatus())
                                                .name(field.getName())
                                                .build();
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                        .collect(groupingBy(ExampleHolder::getCode));

        // -------------------------- 콘텐츠 세팅 코드별로 진행
        addExamplesToResponses(responses, statusWithExampleHolders);
    }

    private Example getSwaggerExample(String value, ErrorReason errorReason) {
        ErrorResponse errorResponse = new ErrorResponse(errorReason, "요청시 패스정보입니다.");
        Example example = new Example();
        example.description(value);
        example.setValue(errorResponse);
        return example;
    }

    private void addExamplesToResponses(
            ApiResponses responses, Map<Integer, List<ExampleHolder>> statusWithExampleHolders) {
        statusWithExampleHolders.forEach(
                (status, v) -> {
                    Content content = new Content();
                    MediaType mediaType = new MediaType();
                    ApiResponse apiResponse = new ApiResponse();
                    v.forEach(
                            exampleHolder -> {
                                mediaType.addExamples(
                                        exampleHolder.getName(), exampleHolder.getHolder());
                            });
                    content.addMediaType("application/json", mediaType);
                    apiResponse.setContent(content);
                    responses.addApiResponse(status.toString(), apiResponse);
                });
    }

    private static List<String> getTags(HandlerMethod handlerMethod) {
        List<String> tags = new ArrayList<>();

        Tag[] methodTags = handlerMethod.getMethod().getAnnotationsByType(Tag.class);
        List<String> methodTagStrings =
                Arrays.stream(methodTags).map(Tag::name).collect(Collectors.toList());

        Tag[] classTags = handlerMethod.getClass().getAnnotationsByType(Tag.class);
        List<String> classTagStrings =
                Arrays.stream(classTags).map(Tag::name).collect(Collectors.toList());
        tags.addAll(methodTagStrings);
        tags.addAll(classTagStrings);
        return tags;
    }
}
