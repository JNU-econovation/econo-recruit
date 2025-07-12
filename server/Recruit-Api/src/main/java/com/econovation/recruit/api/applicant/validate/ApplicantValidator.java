package com.econovation.recruit.api.applicant.validate;

import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantDuplicateSubmitException;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantOutOfDateException;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantWrongPositionException;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicantValidator {
    private final MongoAnswerAdaptor answerAdaptor;
    private final LatestRecruitmentVo latestRecruitInfo;
    private final Integer year;
    private final boolean validateEnabled;

    public ApplicantValidator(MongoAnswerAdaptor answerAdaptor,
                              LatestRecruitmentVo latestRecruitInfo,
                              @Value("${econovation.recruit.valid.enabled}")
                              boolean validateEnabled){

        this.validateEnabled = validateEnabled;
        this.latestRecruitInfo = latestRecruitInfo;
        this.answerAdaptor = answerAdaptor;
        this.year = latestRecruitInfo.getYear().intValue();
    }

    public Validation<Seq<RecruitCodeException>, Map<String, Object>> validateRegisterApplicant(
            Map<String, Object> qna) {
        return Validation.combine(
                        validateDuplicateStudentId(qna),
                        validateIsRightPosition(qna),
                        validateOutdated(qna))
                .ap((a, b, c) -> qna);
    }

    private Validation<RecruitCodeException, Object> validateIsRightPosition(
            Map<String, Object> qna) {
        String field = qna.get("field").toString();
        if (field.equals("기획자") || field.equals("개발자") || field.equals("디자이너")) {
            return Validation.valid(qna);
        }
        throw ApplicantWrongPositionException.EXCEPTION;
    }

    private Validation<RecruitCodeException, Map<String, Object>> validateDuplicateStudentId(
            Map<String, Object> qna) {
        String studentId = qna.get("classOf").toString();
        if (answerAdaptor.existsByAnswer(studentId, year)) {
            throw ApplicantDuplicateSubmitException.EXCEPTION;
        }
        return Validation.valid(qna);
    }

    private Validation<RecruitCodeException, Map<String, Object>> validateOutdated(
            Map<String, Object> qna) {
        if (!validateEnabled) {
            return Validation.valid(qna);
        }

        if (!latestRecruitInfo.getState().equals(RecruitmentStates.RECRUITING)) {
            throw ApplicantOutOfDateException.EXCEPTION;
        }

        return Validation.valid(qna);
    }
}
