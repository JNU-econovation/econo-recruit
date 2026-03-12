package com.econovation.recruit.api.email.handler;

import com.econovation.recruit.api.sms.service.ApplicantSmsService;
import com.econovation.recruitdomain.domains.email_template.event.EmailSendEvent;
import com.econovation.recruitinfrastructure.slack.SlackMessageProvider;
import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSendEventHandler {

    private final ApplicantSmsService smsService;
    private final SlackMessageProvider slackMessageProvider;
    private final SlackProperties slackProperties;

    @Async
    @EventListener(EmailSendEvent.class)
    public void handle(EmailSendEvent emailSendEvent) {
        smsService.sendSms(emailSendEvent.getApplicantId());

        if (emailSendEvent.isSlackNotify()) {
            String url =
                    Objects.isNull(emailSendEvent.getSlackUrl())
                            ? slackProperties.getUrl()
                            : emailSendEvent.getSlackUrl();
            slackMessageProvider.sendMessage(url, generateSlackMessage(emailSendEvent));
        }
    }

    private String generateSlackMessage(EmailSendEvent event) {
        return String.format(
                """
                [메일 발송 성공]
                - 이름 : %s
                - 지원 분야 : %s / %s
                - 합격 상태 : %s
                """,
                event.getName(),
                event.getField1(),
                event.getField2(),
                event.getEmailTemplateType().name());
    }
}
