package com.econovation.recruit.api.card.handler;

import com.econovation.recruit.api.comment.usecase.CommentUseCase;
import com.econovation.recruit.api.label.usecase.LabelUseCase;
import com.econovation.recruitdomain.common.events.WorkCardDeletedEvent;
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
public class WorkCardDeleteEventHandler {
    private final LabelUseCase labelUseCase;
    private final CommentUseCase commentUseCase;

    @Async
    @TransactionalEventListener(
            classes = WorkCardDeletedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(WorkCardDeletedEvent workCardDeletedEvent) {
        labelUseCase.deleteLabelByCardId(workCardDeletedEvent.getCardId());
        commentUseCase.deleteCommentByCardId(workCardDeletedEvent.getCardId());
    }
}
