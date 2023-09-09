package com.econovation.recruit.api.slack.handler;

import com.econovation.recruit.api.card.usecase.BoardRegisterUseCase;
import com.econovation.recruitdomain.domains.applicant.event.ApplicantRegisterEvent;
import com.econovation.recruitdomain.domains.card.adaptor.CardAdaptor;
import com.econovation.recruitdomain.domains.card.domain.Card;
import com.econovation.recruitinfrastructure.slack.SlackMessageProvider;
import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
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
public class ApplicantRegisterEventHandler {
    private final SlackMessageProvider slackMessageProvider;
    private final SlackProperties slackProperties;
    private final CardAdaptor cardAdaptor;
    private final BoardRegisterUseCase boardRegisterUseCase;

    @Async
    @TransactionalEventListener(
            classes = ApplicantRegisterEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(ApplicantRegisterEvent applicantRegistEvent) {
        String message = generateApplicantRegisterMessage(applicantRegistEvent);

        Card card = fromApplicantRegisterEvent(applicantRegistEvent);
        cardAdaptor.save(card);

        boardRegisterUseCase.createApplicantBoard(
                applicantRegistEvent.getApplicantId(),
                applicantRegistEvent.getHopeField(),
                card.getId());

//        slackMessageProvider.sendMessage(slackProperties.getUrl(), message);
    }
    private String generateApplicantRegisterMessage(ApplicantRegisterEvent applicantRegistEvent) {
        return String.format(
                ":clapping: 지원자가 등록되었습니다.:clapping:\n"
                        + ":chikorita: 지원자 이름: %s\n"
                        + ":chikorita:희망 분야: %s",

                applicantRegistEvent.getUserName(), applicantRegistEvent.getHopeField());
    }

    private Card fromApplicantRegisterEvent(ApplicantRegisterEvent event) {
        String title = generateCardTitle(event);
        return Card.builder()
                .applicantId(event.getApplicantId())
                .title(title)
                .applicantId(event.getApplicantId())
                .content("")
                .build();
    }

    private String generateCardTitle(ApplicantRegisterEvent event) {
        return String.format("[%s] %s", extractHopeField(event.getHopeField()), event.getUserName())
                .replace("\"", "");
    }

    private String extractHopeField(String title) {
        String[] titleParts = title.split(" ");
        log.info("hopeField = {}", titleParts[0]);
        return titleParts[0];
    }
}
