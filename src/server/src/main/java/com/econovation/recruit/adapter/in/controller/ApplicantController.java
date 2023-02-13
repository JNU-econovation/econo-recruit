package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.ApplicantRegisterUseCase;
import com.econovation.recruit.application.utils.ApplicantMapper;
import com.econovation.recruit.domain.dto.ApplicantRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantRegisterUseCase applicantRegisterUseCase;
    private final ApplicantMapper applicantMapper;
    @PostMapping("/v1/applicant")
    public ResponseEntity<HttpStatus> registerApplicant(@Valid ApplicantRegisterDto applicantRegisterDto){
        applicantRegisterUseCase.apply(applicantMapper.toEntity(applicantRegisterDto));
    }
}
