package com.econovation.recruit.api.record.controller;

import static com.econovation.recruitcommon.consts.RecruitStatic.RECORD_SUCCESS_CREATE_MESSAGE;

import com.econovation.recruit.api.record.docs.RecordExceptionDocs;
import com.econovation.recruit.api.record.usecase.RecordUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitdomain.domains.dto.CreateRecordDto;
import com.econovation.recruitdomain.domains.dto.RecordResponseDto;
import com.econovation.recruitdomain.domains.record.domain.Record;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "[6.0] Record API", description = "면접 기록 Record API")
@RequiredArgsConstructor
public class RecordController {
    private final RecordUseCase recordUseCase;

    @Operation(summary = "지원자의 면접기록을 생성합니다")
    @PostMapping("/records")
    public ResponseEntity<String> createRecord(@RequestBody CreateRecordDto createRecordDto) {
        recordUseCase.createRecord(createRecordDto);
        return new ResponseEntity(RECORD_SUCCESS_CREATE_MESSAGE, HttpStatus.OK);
    }

    @Operation(summary = "지원자의 면접기록을 조회합니다")
    @ApiErrorExceptionsExample(RecordExceptionDocs.class)
    @GetMapping("/records")
    public ResponseEntity<RecordResponseDto> findByApplicantId(@RequestParam String applicantId) {
        Record record = recordUseCase.findByApplicantId(applicantId);
        return new ResponseEntity(RecordResponseDto.from(record), HttpStatus.OK);
    }
}
