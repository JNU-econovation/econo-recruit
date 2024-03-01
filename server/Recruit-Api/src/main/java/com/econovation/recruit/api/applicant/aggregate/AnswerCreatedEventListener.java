package com.econovation.recruit.api.applicant.aggregate;

import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.event.aggregateevent.AnswerCreatedEvent;
import com.econovation.recruitdomain.domains.applicant.event.domainevent.ApplicantRegisterEvent;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AnswerCreatedEventListener {
    private final MongoAnswerAdaptor answerAdaptor;

    @EventHandler
    @Transactional
    public void handle(AnswerCreatedEvent event) {
        Map<String, Object> qna = event.getQna();
        MongoAnswer answer = new MongoAnswer(event.getId(), event.getYear(), qna);
        // 학번으로 중복 체크
        answerAdaptor.save(answer);

        String name = qna.get("name").toString();
        String hopeField = qna.get("field").toString();
        String email = qna.get("email").toString();

        // email 전송 event처리
        ApplicantRegisterEvent applicantRegisterEvent =
                ApplicantRegisterEvent.of(answer.getId(), name, hopeField, email);
        Events.raise(applicantRegisterEvent);
    }
}
