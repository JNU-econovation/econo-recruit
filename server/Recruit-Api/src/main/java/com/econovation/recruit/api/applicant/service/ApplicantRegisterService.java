package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.command.CreateAnswerCommand;
import com.econovation.recruit.api.applicant.usecase.ApplicantRegisterUseCase;
import com.econovation.recruit.api.applicant.validate.ApplicantValidator;
import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantRegisterService implements ApplicantRegisterUseCase {

    private final ApplicantValidator applicantValidator;
    private final LatestRecruitmentVo latestRecruitInfo;
    private final CommandGateway commandGateway;

    @Override
    public String register(Map<String, Object> qna) {
        applicantValidator.validateRegisterApplicant(qna);
        String applicantId = UUID.randomUUID().toString();
        int year = latestRecruitInfo.getYear();

        commandGateway.send(new CreateAnswerCommand(applicantId, year, qna));

        return applicantId;
    }
}
