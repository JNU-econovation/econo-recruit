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
        // TODO: year에 해당하는 지원자 id 리스트로 뽑기
        List<String> applicantIds = answerAdaptor.findApplicantIdsByYear(year);
        System.out.println(applicantIds);
        mongoAnswerAdaptor.delete(year);
        // TODO: 지원자 id 리스트를 가지고 time_table 데이터 삭제하기 delete(List<String> applicantIds, Integer year)
        timeTableAdapter.deleteAllByApplicantIds(applicantIds);

        // TODO: 지원자 id 리스트를 가지고 score 데이터 삭제하기
        scoreAdaptor.deleteAllByApplicantIds(applicantIds);

        // TODO: 지원자 id 리스트를 가지고 label 데이터 삭제하기


        // TODO: card 테이블에서 지원자 id 리스트에 대응하는 board_id 조회하기

        // TODO: 이전에서 조회한 board_id 리스트에 대응하는 board 데이터 삭제하기

        // TODO: card 테이블에서 지원자 id 리스트에 대응하는 데이터 삭제하기

    }
}
