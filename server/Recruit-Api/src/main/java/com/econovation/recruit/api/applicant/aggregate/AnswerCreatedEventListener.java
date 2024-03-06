package com.econovation.recruit.api.applicant.aggregate;

import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.event.aggregateevent.AnswerCreatedEvent;
import com.econovation.recruitdomain.domains.applicant.event.domainevent.ApplicantRegisterEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AnswerCreatedEventListener {
    private final MongoAnswerAdaptor answerAdaptor;

    @Value("${econovation.recruit.admin.email}")
    private String adminEmail;

    @EventHandler
    @Transactional
    public void handle(AnswerCreatedEvent event) {
        Map<String, Object> qna = event.getQna();
        // 학번으로 중복 체크
        String name = qna.getOrDefault("name", "이름_공백").toString();
        String hopeField = qna.getOrDefault("field", "지원분야_공백").toString();
        String email = qna.getOrDefault("email", adminEmail).toString();
        // backup to json file
        backupApplicant(event);

        // Map 객체에 key가 없는 경우 추가
        qna.putIfAbsent("name", name);
        qna.putIfAbsent("field", hopeField);
        qna.putIfAbsent("email", email);

        MongoAnswer answer = new MongoAnswer(event.getId(), event.getYear(), qna);
        answerAdaptor.save(answer);

        // email 전송 event처리
        ApplicantRegisterEvent applicantRegisterEvent =
                ApplicantRegisterEvent.of(answer.getId(), name, hopeField, email);
        Events.raise(applicantRegisterEvent);
    }

    private void backupApplicant(AnswerCreatedEvent event) {
        Map<String, Object> qna = event.getQna();
        qna.put("id", event.getId());
        qna.put("year", event.getYear());
        qna.put("created_at", LocalDateTime.now().toString());
        try {
            String path = new File(".").getCanonicalPath(); // 현재 작업 디렉토리를 가져옴
            String backupDir = path + "/backup/";
            JSONObject jsonObject = new JSONObject(qna);
            FileWriter file = new FileWriter(backupDir + event.getId() + ".json");
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            log.error("applicantId : " + event.getId() + " / " + qna.toString() + " backup fail");
        }
    }
}
