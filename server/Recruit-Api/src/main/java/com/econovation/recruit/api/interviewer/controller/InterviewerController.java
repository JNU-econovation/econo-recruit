package com.econovation.recruit.api.interviewer.controller;

import static com.econovation.recruitcommon.consts.RecruitStatic.INTERVIEWER_SUCCESS_REGISTER_MESSAGE;

import com.econovation.recruit.api.interviewer.docs.InterviewerExceptionDocs;
import com.econovation.recruit.api.interviewer.usecase.InterviewerUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "[6.0] Interviewer 면접관 API", description = "Interviewer 면접관 API")
@RequestMapping("/api/v1")
public class InterviewerController {
    private final InterviewerUseCase interviewerUseCase;

    @Operation(description = "Interviewer 조회", summary = "면접관 조회")
    @ApiErrorExceptionsExample(InterviewerExceptionDocs.class)
    @GetMapping("/interviewers")
    public ResponseEntity<Interviewer> findByApplicantId(Long idpId) {
        Interviewer interviewer = interviewerUseCase.getById(idpId);
        return new ResponseEntity(interviewer, HttpStatus.OK);
    }

    @PostMapping("/interviewers")
    public ResponseEntity<String> createInterviewers(@RequestBody List<Integer> idpIds) {
        interviewerUseCase.createInterviewers(idpIds);
        return new ResponseEntity<>(INTERVIEWER_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    @PostMapping("/interviewers/name")
    public ResponseEntity<String> createInterviewersByName(@RequestBody List<String> names) {
        interviewerUseCase.createInterviewersByName(names);
        return new ResponseEntity<>(INTERVIEWER_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }
    // IDP를 다 뒤져서 동기화 하기 -> idpId, name, year

    @Operation(description = "Interviewer Role 변경")
    @PutMapping("/interviewers/{idp-id}/roles")
    public ResponseEntity<String> updateRole(
            @PathVariable(name = "idp-id") Long idpId, String role) {
        interviewerUseCase.updateRole(idpId, role);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
