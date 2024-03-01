package com.econovation.recruit.api.applicant.handler;

import static com.econovation.recruitcommon.consts.RecruitStatic.EMPTY_STRING;

import com.econovation.recruit.api.record.usecase.RecordUseCase;
import com.econovation.recruitdomain.domains.applicant.event.domainevent.ApplicantRegisterEvent;
import com.econovation.recruitdomain.domains.dto.CreateRecordDto;
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
public class ApplicantRegisterEventRecordCreateHandler {
    private final RecordUseCase recordUseCase;

    @Async
    @TransactionalEventListener(
            classes = ApplicantRegisterEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(ApplicantRegisterEvent applicantRegistEvent) {
        CreateRecordDto createRecordDto =
                CreateRecordDto.builder()
                        .applicantId(applicantRegistEvent.getApplicantId())
                        .record(EMPTY_STRING)
                        .url(EMPTY_STRING)
                        .build();
        recordUseCase.createRecord(createRecordDto);
    }
}
