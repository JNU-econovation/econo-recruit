package com.econovation.recruit.api.applicant.handler;

import com.econovation.recruit.api.card.usecase.BoardRegisterUseCase;
import com.econovation.recruit.api.card.usecase.CardRegisterUseCase;
import com.econovation.recruitdomain.common.events.applicant.SubmitApplicantEvent;
import com.econovation.recruitdomain.domains.card.Card;
import com.econovation.recruitdomain.persistence.CardAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Slf4j
public class SubmitApplicantEventHandler {
    private final CardRegisterUseCase cardRegisterUseCase;
    private final CardAdapter cardAdapter;
    private final BoardRegisterUseCase boardRegisterUseCase;

    @Async
    @TransactionalEventListener(
            classes = SubmitApplicantEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    public void handle(SubmitApplicantEvent submitApplicantEvent) {
        Card card = createCardFromEvent(submitApplicantEvent);
        cardAdapter.save(card);

        String hopeField = extractHopeField(submitApplicantEvent.getTitle());

        boardRegisterUseCase.createApplicantBoard(
                submitApplicantEvent.getApplicantId(), hopeField, card.getId());
    }

    private Card createCardFromEvent(SubmitApplicantEvent event) {
        return Card.builder().applicantId(event.getApplicantId()).title(event.getTitle()).build();
    }

    private String extractHopeField(String title) {
        String[] titleParts = title.split(" ");
        log.info("hopeField = {}", titleParts[0]);
        return titleParts[0];
    }
}
