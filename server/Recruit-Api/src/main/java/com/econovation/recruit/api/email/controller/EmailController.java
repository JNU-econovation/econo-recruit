package com.econovation.recruit.api.email.controller;

import com.econovation.recruit.api.email.dto.EmailAllSendRequest;
import com.econovation.recruit.api.email.dto.EmailSendRequest;
import com.econovation.recruit.api.email.service.ApplicantEmailService;
import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "access-token")
@RestController
@RequestMapping("/api/v1")
@Tag(name = "[8.0] email 관련 API", description = "메일 API")
@RequiredArgsConstructor
public class EmailController {

    private final ApplicantEmailService emailService;
    private final LatestRecruitmentVo latestRecruitInfo;

    @Operation(
            summary = "지원자에게 맞는 상태의 메일을 보냅니다.",
            description =
                    """
                    서류 결과 메일 -> first-passed / first-failed
                    최종 결과 메일 -> final-passed / final-failed
                    slackNotify: true 로 설정하면 메일 발송 후 Slack 알림을 전송합니다.
                    slackUrl: 미입력 시 서버 기본 Slack URL로 발송합니다.
                    """)
    @PostMapping("/emails/{applicantId}")
    public ResponseEntity<String> send(
            @PathVariable String applicantId,
            @RequestBody(required = false) EmailSendRequest request) {
        boolean slackNotify = request != null && request.isSlackNotify();
        String slackUrl = request != null ? request.getSlackUrl() : null;
        emailService.sendEmail(applicantId, slackNotify, slackUrl);
        return ResponseEntity.ok("");
    }

    @Operation(
            summary = "합격 상태에 맞는 메일을 보냅니다.",
            description =
                    """
                    서류 결과 메일 -> first-passed / first-failed
                    최종 결과 메일 -> final-passed / final-failed
                    slackNotify: true 로 설정하면 메일 발송 후 Slack 알림을 전송합니다.
                    slackUrl: 미입력 시 서버 기본 Slack URL로 발송합니다.
                    """)
    @PostMapping("/emails/all")
    public ResponseEntity<String> sendAll(@RequestBody EmailAllSendRequest request) {
        int year = request.getYear() != null ? request.getYear() : latestRecruitInfo.getYear();
        emailService.sendEmail(
                year, request.getState(), request.isSlackNotify(), request.getSlackUrl());
        return ResponseEntity.ok("이메일 전송 시작");
    }
}
