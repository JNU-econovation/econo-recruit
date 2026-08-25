package com.econovation.recruit.api.email.controller;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruit.api.email.service.ApplicantEmailService;
import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final ApplicantQueryUseCase applicantQueryUseCase;
    private final LatestRecruitmentVo latestRecruitInfo;

    @Operation(
            summary = "지원자에게 맞는 상태의 메일을 보냅니다.",
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
            summary = "합격 상태에 맞는 메일을 보냅니다.",
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
            year = latestRecruitInfo.getYear();
        }

        emailService.sendEmail(year, state);
        return ResponseEntity.ok("이메일 전송 시작");
    }
}
