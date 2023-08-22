package com.econovation.recruit.api.interviewer.controller;

import com.econovation.recruit.api.interviewer.usecase.InterviewerUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitdomain.domains.dto.InterviewerCreateDto;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
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
    public ResponseEntity<List<Interviewer>> createInterviewers(
            @RequestBody List<InterviewerCreateDto> interviewerCreateDto) {
        List<Interviewer> interviewers =
                interviewerUseCase.createInterviewers(interviewerCreateDto);
        return new ResponseEntity<>(interviewers, HttpStatus.OK);
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
