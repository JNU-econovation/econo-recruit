package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.InterviewerUseCase;
import com.econovation.recruit.domain.dto.InterviewerCreateDto;
import com.econovation.recruit.domain.interviewer.Interviewer;
import com.econovation.recruit.domain.label.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InterviewerController {
    private final InterviewerUseCase interviewerUseCase;

//    @GetMapping("/interviewers")
//    public ResponseEntity<> findByApplicantId(Integer idpId) {
//        interviewerUseCase.
//        return new ResponseEntity(labels, HttpStatus.OK);
//    }

    @PostMapping("/interviewers")
    public ResponseEntity<List<Interviewer>> createInterviewers(List<InterviewerCreateDto> interviewerCreateDto) {
        List<Interviewer> interviewers = interviewerUseCase.createInterviewers(interviewerCreateDto);
        return new ResponseEntity<>(interviewers, HttpStatus.OK);
    }
}
