package com.econovation.recruit.api.slack.handler;

import com.econovation.recruitdomain.domains.applicant.event.ApplicantRegisterEvent;
import com.econovation.recruitinfrastructure.slack.SlackMessageProvider;
import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicantRegisterEventHandler {
    private final SlackMessageProvider slackMessageProvider;
    private final SlackProperties slackProperties;

    @Async
    @TransactionalEventListener(
            classes = ApplicantRegisterEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    public void handle(ApplicantRegisterEvent applicantRegistEvent) {
        String message = generateApplicantRegisterMessage(applicantRegistEvent);
        slackMessageProvider.sendMessage(slackProperties.getChannel(), message);
    }

    private String generateApplicantRegisterMessage(ApplicantRegisterEvent applicantRegistEvent) {
        return String.format(
                ":clapping: 지원자가 등록되었습니다.:clapping:\n"
                        + ":chikorita: 지원자 이름: %s\n"
                        + ":chikorita:희망 분야: %s",
                applicantRegistEvent.getUserName(), applicantRegistEvent.getHopeField());
    }
}
