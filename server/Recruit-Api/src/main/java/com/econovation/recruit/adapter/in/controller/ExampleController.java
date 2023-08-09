package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruitcommon.annotation.ApiErrorCodeExample;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/examples")
@RequiredArgsConstructor
// @SecurityRequirement(name = "access-token")
@Tag(name = "xx. [예시] 에러코드 문서화")
public class ExampleController {
    @GetMapping("/applicants")
    @Operation(summary = "지원서 등록 관련 에러코드 나열")
    @ApiErrorCodeExample(ApplicantErrorCode.class)
    public void getPaymentsCreateErrorCode() {}
}
