package com.econovation.recruit.api.email.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailSendRequest {

    @Schema(description = "Slack 알림 발송 여부", example = "true")
    private boolean slackNotify = false;

    @Schema(description = "Slack 채널 URL (없으면 서버 기본 URL 사용)", example = "https://hooks.slack.com/services/...")
    private String slackUrl;
}
