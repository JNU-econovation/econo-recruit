package com.econovation.recruit.api.email.handler;

import com.econovation.recruit.api.sms.service.ApplicantSmsService;
import com.econovation.recruitdomain.domains.email_template.event.EmailSendEvent;
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

    @Async
    @EventListener(EmailSendEvent.class)
    public void handle(EmailSendEvent emailSendEvent) {
        String applicantId = emailSendEvent.getApplicantId();

        smsService.sendSms(applicantId);
    }
}
