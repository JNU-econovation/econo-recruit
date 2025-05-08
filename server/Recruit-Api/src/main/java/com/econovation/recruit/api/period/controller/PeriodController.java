package com.econovation.recruit.api.period.controller;

import com.econovation.recruit.api.period.usecase.PeriodCommandUseCase;
import com.econovation.recruit.api.period.usecase.PeriodQueryUseCase;
import com.econovation.recruitdomain.domains.dto.CreatePeriodDto;
import com.econovation.recruitdomain.domains.dto.PeriodResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "[9.0] 리크루트 기간 API", description = "리크루트의 기간을 설정할 수 있는 API 입니다.")
@RequestMapping("/api/v1")
public class PeriodController {

    private final PeriodQueryUseCase periodQueryUseCase;
    private final PeriodCommandUseCase periodCommandUseCase;

    @GetMapping("/periods")
    public ResponseEntity<PeriodResponseDto> get() {
        return new ResponseEntity<>(periodQueryUseCase.get(), HttpStatus.OK);
    }

    @PostMapping("/periods")
    public ResponseEntity<Void> create(@RequestBody CreatePeriodDto request) {
        periodCommandUseCase.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
