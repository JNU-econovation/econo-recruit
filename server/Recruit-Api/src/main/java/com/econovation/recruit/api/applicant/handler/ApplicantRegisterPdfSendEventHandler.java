package com.econovation.recruit.api.applicant.handler;

import com.econovation.recruitdomain.domains.applicant.event.ApplicantRegisterEvent;
import com.econovation.recruitdomain.domains.applicant.helper.ApplicantPdfHelper;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicantRegisterPdfSendEventHandler {
    private final ApplicantPdfHelper applicantPdfHelper;

    @Async
    @TransactionalEventListener(
            classes = ApplicantRegisterEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    @Transactional
    public void handle(ApplicantRegisterEvent applicantRegistEvent) throws MessagingException {
        // TODO Log 제거 예정
        log.info("applicantRegistEvent: " + applicantRegistEvent.toString());
        applicantPdfHelper.sendToInterviewers(applicantRegistEvent.getAnswers());
    }
}
