package com.econovation.recruit.api.recruitment.controller;

import com.econovation.recruit.api.applicant.command.ChangeRecruitmentCommand;
import com.econovation.recruit.api.recruitment.usecase.RecruitmentUseCase;
import com.econovation.recruitdomain.domains.applicant.dto.RecruitmentStateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "[9.0]. 리크루트 모집 API", description = "리크루트 모집 관련 API")
public class RecruitmentController {

    private final RecruitmentUseCase recruitmentUseCase;

    @Operation(
            summary = "지원서 접수를 시작합니다.",
            description = "지원서 접수를 즉시 시작하거나 정해진 시간에 시작할 수 있도록 상태를 변경합니다."
    )
    @PostMapping("/recruitment/state")
    public ResponseEntity<Boolean> changeRecruitment(@RequestBody RecruitmentStateDto request){
        boolean result = recruitmentUseCase.changeState(new ChangeRecruitmentCommand(request.getStates(), request.getYear(), request.getReservedAt()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
