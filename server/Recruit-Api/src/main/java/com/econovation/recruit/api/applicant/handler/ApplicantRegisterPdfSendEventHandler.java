package com.econovation.recruit.api.applicant.handler;

import com.econovation.recruitdomain.domains.applicant.dto.ApplicantPdfDto;
import com.econovation.recruitdomain.domains.applicant.event.ApplicantRegisterEvent;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicantRegisterPdfSendEventHandler {

    @Async
    @TransactionalEventListener(
            classes = ApplicantRegisterEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    @Transactional
    public void handle(ApplicantRegisterEvent applicantRegistEvent) {
        ApplicantPdfDto applicantPdfDto = createApplicantPdfDto(applicantRegistEvent.getAnswers());
    }

    private ApplicantPdfDto createApplicantPdfDto(Map<String, String> result) {
        return ApplicantPdfDto.builder()
                .field(result.get("field"))
                .field1(result.get("field1"))
                .field2(result.get("field2"))
                .name(result.get("name"))
                .contacted(result.get("contacted"))
                .classOf(result.get("classOf"))
                .grade(result.get("grade"))
                .semester(result.get("semester"))
                .major(result.get("major"))
                .doubleMajor(result.get("doubleMajor"))
                .minor(result.get("minor"))
                .activity(result.get("activity"))
                .channel(result.get("channel"))
                .reason(result.get("reason"))
                .future(result.get("future"))
                .experience(result.get("experience"))
                .experienceTextarea(result.get("experienceTextarea"))
                .goal(result.get("goal"))
                .deep(result.get("deep"))
                .collaboration(result.get("collaboration"))
                .portfolio(result.get("portfolio"))
                .fileUrl(result.get("fileUrl"))
                .fileUrlforPlanner(result.get("fileUrlforPlanner"))
                .email(result.get("email"))
                .check(result.get("check"))
                .personalInformationAgree(result.get("personalInformationAgree"))
                .personalInformationAgreeForPortfolio(
                        result.get("personalInformationAgreeForPortfolio"))
                .build();
    }
}
