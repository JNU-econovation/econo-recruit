package com.econovation.recruit.api.label.controller;

import static com.econovation.recruitcommon.consts.RecruitStatic.*;

import com.econovation.recruit.api.label.docs.LabelDeleteExceptionDocs;
import com.econovation.recruit.api.label.docs.LabelExceptionDocs;
import com.econovation.recruit.api.label.usecase.LabelUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "[5.0] 라벨 API", description = "라벨 관련 (카드 좋아요) API")
@RequestMapping("/api/v1")
public class LabelController {
    private final LabelUseCase labelUseCase;

    @Operation(summary = "지원자의 라벨을 조회합니다.")
    @ApiErrorExceptionsExample(LabelExceptionDocs.class)
    @GetMapping("/applicants/{applicant-id}/labels")
    public ResponseEntity<List<String>> findByApplicantId(
            @PathVariable(name = "applicant-id") String applicantId) {
        List<String> interviewerNames = labelUseCase.findByApplicantId(applicantId);
        return new ResponseEntity(interviewerNames, HttpStatus.OK);
    }

    @Operation(summary = "지원자의 라벨을 생성합니다. , 라벨이 이미 존재할 경우 삭제합니다.")
    @PostMapping("/applicants/{applicant-id}/labels")
    @ApiErrorExceptionsExample(LabelExceptionDocs.class)
    public ResponseEntity<String> createLabel(
            @PathVariable(name = "applicant-id") String applicantId) {
        Boolean success = labelUseCase.createLabel(applicantId);
        if (success) {
            return new ResponseEntity<>(LABEL_SUCCESS_CREATE_MESSAGE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(LABEL_SUCCESS_DELETE_IN_CREATE_MESSAGE, HttpStatus.OK);
        }
    }

    @Operation(summary = "지원자의 라벨을 취소합니다.")
    @DeleteMapping("/applicants/{applicant-id}/labels")
    @ApiErrorExceptionsExample(LabelDeleteExceptionDocs.class)
    public ResponseEntity<String> deleteLabel(
            @PathVariable(name = "applicant-id") String applicantId) {
        labelUseCase.deleteLabel(applicantId);
        return new ResponseEntity<>(LABEL_SUCCESS_DELETE_MESSAGE, HttpStatus.OK);
    }
}
