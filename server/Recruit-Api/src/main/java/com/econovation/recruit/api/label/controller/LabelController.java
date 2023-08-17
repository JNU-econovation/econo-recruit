package com.econovation.recruit.api.label.controller;

import static com.econovation.recruitcommon.consts.RecruitStatic.LABEL_SUCCESS_DELETE_MESSAGE;

import com.econovation.recruit.api.label.docs.LabelExceptionDocs;
import com.econovation.recruit.api.label.usecase.LabelUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitdomain.domains.label.Label;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "[3.0] 라벨 API", description = "라벨 관련 (카드 좋아요) API")
@RequestMapping("/api/v1")
public class LabelController {
    private final LabelUseCase labelUseCase;

    @Operation(summary = "지원자의 라벨을 조회합니다.")
    @ApiErrorExceptionsExample(LabelExceptionDocs.class)
    @GetMapping("/labels")
    public ResponseEntity<List<String>> findByApplicantId(Integer applicantId) {
        List<String> interviewerNames = labelUseCase.findByApplicantId(applicantId);
        return new ResponseEntity(interviewerNames, HttpStatus.OK);
    }

    @Operation(summary = "지원자의 라벨을 생성합니다.")
    @PostMapping("/labels")
    @ApiErrorExceptionsExample(LabelExceptionDocs.class)
    public ResponseEntity<Label> createLabel(Integer applicantId, Integer idpId) {
        Label label = labelUseCase.createLabel(applicantId, idpId);
        return new ResponseEntity<>(label, HttpStatus.OK);
    }

    @Operation(summary = "지원자의 라벨을 취소합니다.")
    @DeleteMapping("/labels")
    @ApiErrorExceptionsExample(LabelExceptionDocs.class)
    public ResponseEntity<String> deleteLabel(Integer applicantId, Integer idpId) {
        labelUseCase.deleteLabel(applicantId, idpId);
        return new ResponseEntity<>(LABEL_SUCCESS_DELETE_MESSAGE, HttpStatus.OK);
    }
}
