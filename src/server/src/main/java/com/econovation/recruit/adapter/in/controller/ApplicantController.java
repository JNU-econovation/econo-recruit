package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.ApplicantRegisterUseCase;
import com.econovation.recruit.application.utils.EntityMapper;
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
    private static final String APPLT_SUCCESS_MESSAGE = "성공적으로 지원됐습니다";
    private final ApplicantRegisterUseCase applicantRegisterUseCase;
    private final EntityMapper applicantMapper;
    @PostMapping("/v1/applicant")
    public ResponseEntity registerApplicant(@Valid ApplicantRegisterDto applicantRegisterDto){
        applicantRegisterUseCase.apply(applicantMapper.toApplicant(applicantRegisterDto));
        return new ResponseEntity<>(APPLT_SUCCESS_MESSAGE,HttpStatus.OK);
    }
}
