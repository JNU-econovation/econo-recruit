package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantCommandUseCase;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.event.domainevent.ApplicantRegisterEvent;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantDuplicateSubmitException;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantWrongPositionException;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerCommandService implements ApplicantCommandUseCase {
    private final MongoAnswerAdaptor answerAdaptor;

    @Value("${econovation.year}")
    private Integer year;

    @Override
    public UUID execute(Map<String, Object> qna) {
        UUID id = UUID.randomUUID();
        MongoAnswer answer = MongoAnswer.builder().id(id.toString()).qna(qna).year(year).build();
        // 학번으로 중복 체크
        validateRegisterApplicant(qna);
        answerAdaptor.save(answer);

        String name = qna.get("name").toString();
        String hopeField = qna.get("field").toString();
        String email = qna.get("email").toString();

        ApplicantRegisterEvent applicantRegisterEvent =
                ApplicantRegisterEvent.of(answer.getId(), name, hopeField, email);
        Events.raise(applicantRegisterEvent);
        return id;
    }

    private Validation<Seq<RecruitCodeException>, Map<String, Object>> validateRegisterApplicant(
            Map<String, Object> qna) {
        return Validation.combine(validateDuplicateSudentId(qna), validateIsRightPosition(qna))
                .ap((a, b) -> qna);
    }

    private Validation<RecruitCodeException, Object> validateIsRightPosition(
            Map<String, Object> qna) {
        String field = qna.get("field").toString();
        if (field.equals("기획자") || field.equals("개발자") || field.equals("디자이너")) {
            return Validation.valid(qna);
        }
        throw ApplicantWrongPositionException.EXCEPTION;
    }

    private Validation<RecruitCodeException, Map<String, Object>> validateDuplicateSudentId(
            Map<String, Object> qna) {
        String studentId = qna.get("classOf").toString();
        if (answerAdaptor.existsByAnswer(studentId, year)) {
            throw ApplicantDuplicateSubmitException.EXCEPTION;
        }
        return Validation.valid(qna);
    }
}
