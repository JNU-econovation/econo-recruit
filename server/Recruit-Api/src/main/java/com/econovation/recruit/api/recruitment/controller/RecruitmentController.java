package com.econovation.recruit.api.recruitment.controller;

import com.econovation.recruit.api.recruitment.dto.RecruitmentResponsesDto;
import com.econovation.recruit.api.recruitment.usecase.RecruitmentUseCase;
import com.econovation.recruit.utils.vo.PageInfo;
import com.econovation.recruitdomain.domains.dto.RecruitmentResponseDto;
import com.econovation.recruitdomain.domains.dto.RecruitmentSetUpDto;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.exception.RecruitmentInValidDateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            summary = "지원서 접수를 시작합니다. (관리자,회장단)",
            description =
                    """
                    - 몇 기를, 언제부터 언제까지 모집할 것인지에 대한 정보를 RequestBody 로 받습니다.\n\n
                    - 서버는 해당 날짜가 되면 자동으로 지원서 접수를 open 하고, close 합니다.\n\n
                    - 만약, 현재 예약 중인 모집이 1개 이상 존재한다면 서버는 상태코드 500으로 응답하고, 해당 요청에 대해서는 예약을 하지 않습니다.\n\n
                    - 서버는 최대로 예약 가능한 모집이 1개입니다.\n\n
                    - startAt, endAt 시간의 단위는 timestamp ms 입니다.
                    """)
    @PostMapping("/recruitment")
    public ResponseEntity<Long> setUpRecruitment(@RequestBody RecruitmentSetUpDto request) {
        LocalDateTime startAt = request.getStartAt();
        LocalDateTime endAt = request.getEndAt();

        if (startAt.isAfter(endAt)) throw RecruitmentInValidDateException.EXCEPTION_1;
        if (startAt.isBefore(LocalDateTime.now()))
            throw RecruitmentInValidDateException.EXCEPTION_2;

        Long recruitmentId = recruitmentUseCase.setUp(request.getYear(), startAt, endAt);
        return new ResponseEntity<>(recruitmentId, HttpStatus.OK);
    }

    @Operation(
            summary = "지원서 접수를 종료합니다.",
            description =
                    """
                    - 사용하여 예약 중인 작업을 취소합니다.
                    - 만약, 이미 open 된 경우라면 즉각 종료합니다.
                    """)
    @DeleteMapping("/recruitments/{recruitmentId}")
    public ResponseEntity<String> terminateRecruitment(
            @PathVariable("recruitmentId") Long recruitmentId) {
        recruitmentUseCase.terminate(recruitmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "지원서 모집 리스트를 조회합니다. (최신순 정렬)",
            description =
                    """
                    - 기존에 모집했던 이력들을 조회합니다.
                    - 만약, 현재 예약 중인 모집이 있다면 응답 결과에 포함됩니다.
                    - 한 페이지의 크기는 5 입니다.
                    """)
    @GetMapping("/page/{page}/recruitments")
    public ResponseEntity<RecruitmentResponsesDto> getRecruitments(@PathVariable("page") int page) {
        int pageSize = 5; // pageSize 설정
        List<RecruitmentResponseDto> recruitments =
                recruitmentUseCase.getPage(page, pageSize).stream()
                        .map(RecruitmentResponseDto::create)
                        .toList();
        PageInfo pageInfo = new PageInfo(recruitments.size(), page, pageSize);
        RecruitmentResponsesDto response = new RecruitmentResponsesDto(pageInfo, recruitments);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "가장 최근의 모집을 조회합니다.",
            description = """
                    - 가장 최근의 모집을 조회합니다.
                    """)
    @GetMapping("/recruitment")
    public ResponseEntity<RecruitmentResponseDto> getLatestRecruitments() {
        Recruitment recruitment = recruitmentUseCase.getLatestOne();
        RecruitmentResponseDto response = RecruitmentResponseDto.create(recruitment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
