package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.ApplicantRegisterUseCase;
import com.econovation.recruit.application.port.in.CardRegisterUseCase;
import com.econovation.recruit.application.port.out.LoadApplicantPort;
import com.econovation.recruit.application.port.out.RecordApplicantPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.card.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicantService implements ApplicantRegisterUseCase {
    private static final String DUPLICATED_APPLICANT_EXCEPTION = "중복된 지원자입니다.";
    private final CardRegisterUseCase cardRegisterUseCase;
    private final LoadApplicantPort loadApplicantPort;
    private final RecordApplicantPort recordApplicantPort;
    @Override
    @Transactional
    public void apply(Applicant applicant) {
        //  applicant 중복 검사
        if(isDuplicate(applicant)){
            throw new IllegalArgumentException(DUPLICATED_APPLICANT_EXCEPTION);
        }
        // 최신 card 생성
        Card card = cardRegisterUseCase.saveApplicantCard(applicant);
        applicant.presetCard(card);
        recordApplicantPort.save(applicant);
    }

    private Boolean isDuplicate(Applicant applicant) {
        return loadApplicantPort.isDuplicate(applicant);
    }
}
