package com.econovation.recruit.api.email.handler;

import com.econovation.recruit.api.sms.service.ApplicantSmsService;
import com.econovation.recruitdomain.domains.email_template.event.EmailSendEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSendEventHandler {

    private final ApplicantSmsService smsService;

    @Async
    @TransactionalEventListener(
            classes = EmailSendEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(EmailSendEvent emailSendEvent) {
        String applicantId = emailSendEvent.getApplicantId();

        smsService.sendSms(applicantId);
    }
}
