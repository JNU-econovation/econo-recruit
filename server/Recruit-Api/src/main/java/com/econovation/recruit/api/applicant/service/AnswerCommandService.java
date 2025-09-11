package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.handler.ApplicantStateUpdateEventHandler;
import com.econovation.recruit.api.applicant.usecase.ApplicantCommandUseCase;
import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.domains.applicant.adaptor.AnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.domain.state.ApplicantState;
import com.econovation.recruitdomain.domains.applicant.event.domainevent.ApplicantRegisterEvent;
import com.econovation.recruitdomain.domains.applicant.event.domainevent.ApplicantStateModifyEvent;
import com.econovation.recruitdomain.domains.board.adaptor.BoardAdaptor;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.card.adaptor.CardAdaptor;
import com.econovation.recruitdomain.domains.card.domain.Card;
import com.econovation.recruitdomain.domains.label.adaptor.LabelAdaptor;
import com.econovation.recruitdomain.domains.score.adaptor.ScoreAdaptor;
import com.econovation.recruitdomain.domains.timetable.adaptor.TimeTableAdapter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerCommandService implements ApplicantCommandUseCase {
    private final MongoAnswerAdaptor mongoAnswerAdaptor;
    private final ApplicantStateUpdateEventHandler applicantStateUpdateEventHandler;
    private final LatestRecruitmentVo latestRecruitInfo;
    private final AnswerAdaptor answerAdaptor;
    private final TimeTableAdapter timeTableAdapter;
    private final ScoreAdaptor scoreAdaptor;
    private final LabelAdaptor labelAdaptor;
    private final CardAdaptor cardAdaptor;
    private final BoardAdaptor boardAdaptor;

    @Override
    @Transactional
    public UUID execute(Map<String, Object> qna) {
        UUID id = UUID.randomUUID();
        execute(qna, id);
        return id;
    }

    @Override
    @Transactional
    public String execute(String applicantId, String afterState) {
        ApplicantStateModifyEvent stateModifyEventEvents =
                ApplicantStateModifyEvent.of(applicantId, afterState);
        return applicantStateUpdateEventHandler.handle(stateModifyEventEvents); // 동기로 처리
    }

    @Override
    public UUID execute(Map<String, Object> qna, UUID id) {
        int year = latestRecruitInfo.getYear();
        ApplicantState nonProcessed = new ApplicantState();
        MongoAnswer answer =
                MongoAnswer.builder()
                        .id(id.toString())
                        .qna(qna)
                        .year(year)
                        .applicantState(nonProcessed)
                        .build();
        //        학번으로 중복 체크
        //        validateRegisterApplicant(qna);
        mongoAnswerAdaptor.save(answer);

        String name = qna.get("name").toString();
        String hopeField = qna.get("field").toString();
        String email = qna.get("email").toString();

        ApplicantRegisterEvent applicantRegisterEvent =
                ApplicantRegisterEvent.of(answer.getId(), name, hopeField, email);
        Events.raise(applicantRegisterEvent);
        return null;
    }

    @Override
    @Transactional
    public void deleteByYear(Integer year) {
        List<String> applicantIds = answerAdaptor.findApplicantIdsByYear(year);
        mongoAnswerAdaptor.delete(year);

        timeTableAdapter.deleteAllByApplicantIds(applicantIds);
        scoreAdaptor.deleteAllByApplicantIds(applicantIds);
        labelAdaptor.deleteAllByApplicantIds(applicantIds);

        List<Long> cardIds = cardAdaptor.findAllByApplicantIds(applicantIds);
        boardAdaptor.deleteAllByCardIds(cardIds);
        cardAdaptor.deleteAllByApplicantIds(applicantIds);
    }

    @Override
    @Transactional
    public void deleteByApplicantIds(List<String> applicantIds) {
        mongoAnswerAdaptor.deleteByApplicantIds(applicantIds);

        timeTableAdapter.deleteAllByApplicantIds(applicantIds);
        scoreAdaptor.deleteAllByApplicantIds(applicantIds);
        labelAdaptor.deleteAllByApplicantIds(applicantIds);

        for (String applicantId : applicantIds) {
            deleteBoardByApplicantId(applicantId);
        }

        cardAdaptor.deleteAllByApplicantIds(applicantIds);
    }

    private void deleteBoardByApplicantId(String applicantId) {
        Card card = cardAdaptor.findByApplicantId(applicantId);
        Board deleteBoard = boardAdaptor.getBoardByCardId(card.getId());

        Board previousBoard = boardAdaptor.getByNextBoardId(deleteBoard.getId()).get();
        previousBoard.updateNextBoardID(deleteBoard.getNextBoardId());

        boardAdaptor.deleteByCardId(card.getId());
    }
}
