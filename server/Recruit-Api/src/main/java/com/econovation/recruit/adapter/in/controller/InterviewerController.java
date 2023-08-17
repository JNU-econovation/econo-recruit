package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.api.interviewer.usecase.InterviewerUseCase;
import com.econovation.recruitdomain.domains.dto.InterviewerCreateDto;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
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

    @GetMapping("/interviewers")
    public ResponseEntity<Interviewer> findByApplicantId(Integer idpId) {
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
}
