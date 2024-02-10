package com.econovation.recruit.api.applicant.controller;

import com.econovation.recruit.api.applicant.docs.CreateApplicantExceptionDocs;
import com.econovation.recruit.api.applicant.usecase.ApplicantMongoLoadUseCase;
import com.econovation.recruit.api.applicant.usecase.ApplicantMongoRegisterUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitcommon.annotation.XssProtected;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AnswerController {
    private final ApplicantMongoRegisterUseCase applicantMongoRegisterUseCase;
    private final ApplicantMongoLoadUseCase applicantMongoLoadUseCase;

    @Operation(summary = "지원자가 지원서를 작성합니다.", description = "반환 값은 생성된 지원자의 ID입니다.")
    @ApiErrorExceptionsExample(CreateApplicantExceptionDocs.class)
    @XssProtected
    @PostMapping("/register")
    public ResponseEntity registerMongoApplicant(@RequestBody Map<String, Object> qna) {
        applicantMongoRegisterUseCase.execute(qna);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "지원자 id로 지원서를 조회합니다.")
    @GetMapping("/applicants/mongo/{applicant-id}")
    public ResponseEntity<Map<String, Object>> getApplicantById(
            @PathVariable(value = "applicant-id") String applicantId) {
        return new ResponseEntity<>(applicantMongoLoadUseCase.execute(applicantId), HttpStatus.OK);
    }
}
