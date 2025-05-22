package com.econovation.recruit.api.email.controller;

import com.econovation.recruit.api.email.service.ApplicantEmailReserveService;
import com.econovation.recruit.api.email.service.ApplicantEmailService;
import com.econovation.recruitdomain.domains.dto.EmailReservationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "access-token")
@RestController
@RequestMapping("/api/v1")
@Tag(name = "[8.0] email 관련 API", description = "메일 API")
@RequiredArgsConstructor
public class EmailController {

    private final ApplicantEmailService emailService;
    private final ApplicantEmailReserveService emailReserveService;

    @Value("${econovation.year}")
    private int year;

    @Operation(
            summary = "지원자 한 명에게 메일을 보냅니다.",
            description =
                    """
                    서류 결과 메일 -> first-passed / first-failed
                    최종 결과 메일 -> final-passed / final-failed
                    """)
    @PostMapping("/emails/{applicantId}")
    public ResponseEntity<String> send(@PathVariable String applicantId) {
        emailService.sendEmail(applicantId);
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "여러명의 지원자에게 메일을 보냅니다.",
            description =
                    """
                    서류 결과 메일 -> first-passed / first-failed
                    최종 결과 메일 -> final-passed / final-failed
                    """)
    @PostMapping("/emails/all")
    public ResponseEntity<String> sendAll(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "state") String state) {
        if (year == null || state == null) {
            year = this.year;
        }

        emailService.sendEmail(year, state);
        return ResponseEntity.ok("이메일 전송 시작");
    }

    @Operation(
            summary = "지원자에게 보낼 메일을 예약합니다.",
            description =
                    """
                    List<String> : 지원자의 id 리스트
                    long : 메일 발송 예약일의 timestamp
                    """
    )
    @PostMapping("/emails/all/reservation")
    public ResponseEntity<String> reserve(@RequestBody EmailReservationDto request){

        LocalDateTime reservedAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(request.getReservedAtTimestamp()), ZoneId.of("Asia/Seoul"));

        emailReserveService.reserve(request.getApplicantIds(), reservedAt);

        return ResponseEntity.ok("예약 성공");
    }
}
