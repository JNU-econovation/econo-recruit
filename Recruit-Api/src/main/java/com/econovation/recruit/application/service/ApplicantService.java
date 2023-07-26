package com.econovation.recruit.application.service;


import com.econovation.recruit.application.port.in.ApplicantRegisterUseCase;
import com.econovation.recruit.application.port.in.CardRegisterUseCase;
import com.econovation.recruitdomain.out.ApplicantLoadPort;
import com.econovation.recruitdomain.out.ApplicantRecordPort;
import com.econovation.recruitdomain.domain.applicant.Applicant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicantService implements ApplicantRegisterUseCase {
    private static final String DUPLICATED_APPLICANT_EXCEPTION = "중복된 지원자입니다.";
    private final CardRegisterUseCase cardRegisterUseCase;
    private final ApplicantLoadPort applicantLoadPort;
    private final ApplicantRecordPort applicantRecordPort;

    @Override
    //    @Transactional(rollbackFor = {IllegalStateException.class})
    public void apply(Applicant applicant) {
        //  applicant 중복 검사
        if (isDuplicate(applicant)) {
            throw new IllegalArgumentException(DUPLICATED_APPLICANT_EXCEPTION);
        }
        // 최신 card 생성
        cardRegisterUseCase.saveApplicantCard(applicant);
        applicantRecordPort.save(applicant);
    }

    @Transactional(readOnly = true)
    Boolean isDuplicate(Applicant applicant) {
        return applicantLoadPort.isDuplicate(applicant);
    }
}
