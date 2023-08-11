package com.econovation.recruit.api.applicant.handler;

import com.econovation.recruit.api.card.usecase.BoardRegisterUseCase;
import com.econovation.recruit.api.card.usecase.CardRegisterUseCase;
import com.econovation.recruitdomain.common.events.applicant.SubmitApplicantEvent;
import com.econovation.recruitdomain.domains.board.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubmitApplicantEventHandler {
    private final CardRegisterUseCase cardRegisterUseCase;
    private final BoardRegisterUseCase boardRegisterUseCase;
    @Async
    @TransactionalEventListener(
            classes = SubmitApplicantEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    public void handle(SubmitApplicantEvent submitApplicantEvent) {
//        Board 생성
        Board board = boardRegisterUseCase.createApplicantBoard(submitApplicantEvent.getApplicantId());
        // Card 생성
        cardRegisterUseCase.

    }
}
