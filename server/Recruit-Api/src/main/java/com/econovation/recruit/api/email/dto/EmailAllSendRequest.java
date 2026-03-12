package com.econovation.recruit.api.email.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailAllSendRequest {

    @Schema(description = "발송 연도 (없으면 최신 모집 연도 사용)", example = "2026")
    private Integer year;

    @Schema(description = "합격 상태 (first-passed / first-failed / final-passed / final-failed)", example = "final-passed")
    private String state;

    @Schema(description = "Slack 알림 발송 여부", example = "true")
    private boolean slackNotify = false;

    @Schema(description = "Slack 채널 URL (없으면 서버 기본 URL 사용)", example = "https://hooks.slack.com/services/...")
    private String slackUrl;
}
