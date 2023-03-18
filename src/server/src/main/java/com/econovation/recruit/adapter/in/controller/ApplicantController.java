package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.ApplicantRegisterUseCase;
import com.econovation.recruit.application.utils.EntityMapper;
import com.econovation.recruit.domain.dto.ApplicantRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApplicantController {
    private static final String APPLY_SUCCESS_MESSAGE = "성공적으로 지원됐습니다";
    private final ApplicantRegisterUseCase applicantRegisterUseCase;
    private final EntityMapper applicantMapper;
    @PostMapping("/applicants")
    public ResponseEntity registerApplicant(@RequestBody ApplicantRegisterDto applicantRegisterDto){
        applicantRegisterUseCase.apply(applicantMapper.toApplicant(applicantRegisterDto));
        return new ResponseEntity<>(APPLY_SUCCESS_MESSAGE,HttpStatus.OK);
    }
}
