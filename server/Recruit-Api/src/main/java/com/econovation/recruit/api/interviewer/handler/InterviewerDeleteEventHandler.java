package com.econovation.recruit.api.interviewer.handler;

import com.econovation.recruitdomain.domains.interviewer.domainevent.InterviewerDeleteEvent;
import com.econovation.recruitdomain.out.CommentLikeRecordPort;
import com.econovation.recruitdomain.out.CommentRecordPort;
import com.econovation.recruitdomain.out.LabelRecordPort;
import com.econovation.recruitdomain.out.ScoreRecordPort;
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
public class InterviewerDeleteEventHandler {

    private final ScoreRecordPort scoreRecordPort;
    private final LabelRecordPort labelRecordPort;
    private final CommentLikeRecordPort commentLikeRecordPort;
    private final CommentRecordPort commentRecordPort;

    @Async
    @TransactionalEventListener(
            classes = InterviewerDeleteEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(InterviewerDeleteEvent interviewerDeleteEvent) {
        Long idpId = interviewerDeleteEvent.getInterviewer().getId();
        deleteScoreRecordByInterviewerId(idpId);
        deleteLabelRecordByInterviewerId(idpId);
        deleteCommentLikeRecordByInterviewerId(idpId);
        deleteCommentRecordByInterviewerId(idpId);
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteScoreRecordByInterviewerId(Long interviewerId) {
        scoreRecordPort.deleteByInterviewerId(interviewerId);
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteLabelRecordByInterviewerId(Long interviewerId) {
        labelRecordPort.deleteByInterviewerId(interviewerId);
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteCommentLikeRecordByInterviewerId(Long interviewerId) {
        commentLikeRecordPort.deleteByInterviewerId(interviewerId);
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteCommentRecordByInterviewerId(Long interviewerId) {
        commentRecordPort.deleteByInterviewerId(interviewerId);
    }
}
