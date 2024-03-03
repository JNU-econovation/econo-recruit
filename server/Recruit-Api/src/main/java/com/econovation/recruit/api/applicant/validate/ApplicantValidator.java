package com.econovation.recruit.api.applicant.validate;

import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswerAdaptor;
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
@RequiredArgsConstructor
public class ApplicantValidator {
    private final MongoAnswerAdaptor answerAdaptor;

    @Value("${econovation.recruit.period.start}")
    private String recruitPeriodStart;

    @Value("${econovation.recruit.period.end}")
    private String recruitPeriodEnd;

    @Value("${econovation.year}")
    private Integer year;

    @Value("${econovation.recruit.valid.enabled}")
    private boolean validateEnabled;

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
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime currentKoreaTime = ZonedDateTime.now(koreaZoneId);

        // 비교할 날짜와 시간 설정 (시작 시간과 끝 시간을 설정해두어야 한다.)
        LocalDateTime recruitPeriodStartDateTime = LocalDateTime.parse(recruitPeriodStart);
        LocalDateTime recruitPeriodEndDateTime = LocalDateTime.parse(recruitPeriodEnd);
        ZonedDateTime recruitPeriodStartZoneDateTime =
                ZonedDateTime.of(recruitPeriodStartDateTime, koreaZoneId);
        ZonedDateTime recruitPeriodEndZoneDateTime =
                ZonedDateTime.of(recruitPeriodEndDateTime, koreaZoneId);

        // 현재 시간이 2023년 09월 16일 00시 00분 00초 (한국 시간) 이후인지 확인
        boolean isOutdated =
                currentKoreaTime.isBefore(recruitPeriodStartZoneDateTime)
                        || currentKoreaTime.isAfter(recruitPeriodEndZoneDateTime);
        if (isOutdated) {
            throw ApplicantOutOfDateException.EXCEPTION;
        }
        return Validation.valid(qna);
    }
}
