package com.econovation.recruit.api.applicant.handler;

import com.econovation.recruitdomain.domains.applicant.event.domainevent.ApplicantRegisterEvent;
import com.econovation.recruitinfrastructure.apache.CommonsEmailSender;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "notification.email.enabled",
        havingValue = "true",
        matchIfMissing = false)
@Slf4j
public class ApplicantRegisterEventConfirmEmailHandler {
    private final CommonsEmailSender commonsEmailSender;

    @Value("${econovation.year}")
    private Integer year;

    @Value("${econovation.recruit.period.passedDate}")
    private String confirmRegisterEmail;

    @Async
    @TransactionalEventListener(
            classes = ApplicantRegisterEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(ApplicantRegisterEvent applicantRegistEvent) {
        LocalDateTime passedDate = LocalDateTime.parse(confirmRegisterEmail);
        commonsEmailSender.send(
                applicantRegistEvent.getEmail(), applicantRegistEvent.getApplicantId(), passedDate);
    }
}
