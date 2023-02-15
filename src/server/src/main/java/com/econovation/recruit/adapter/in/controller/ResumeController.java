package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.TimeTableUseCase;
import com.econovation.recruit.application.port.in.ResumeUseCase;
import com.econovation.recruit.domain.dto.ResumeInsertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResumeController {
    private final ResumeUseCase resumeUseCase;
    private final TimeTableUseCase timeTableUseCase;
    @PostMapping("/resume")
    public ResponseEntity< List<ResumeInsertDto> > resumeUseCase(@RequestBody HashMap<String, Object> param){
        List<ResumeInsertDto> resumeInsertDtos = resumeUseCase.submitResume(param);
        return new ResponseEntity<>(resumeInsertDtos, HttpStatus.OK);
    }

}
