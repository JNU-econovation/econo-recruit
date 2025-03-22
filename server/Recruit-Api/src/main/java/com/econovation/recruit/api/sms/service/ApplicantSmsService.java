package com.econovation.recruit.api.sms.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruit.api.sms.util.SmsMessageGenerator;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitinfrastructure.ncp.NcpSmsSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicantSmsService {

    @Value("${econovation.year}")
    private String year;

    private final NcpSmsSender smsSender;
    private final SmsMessageGenerator messageGenerator;
    private final ApplicantQueryUseCase applicantQueryUseCase;

    public void sendSms(MongoAnswer applicant){
        String phoneNumber = applicant.getQna().get("phone").toString();
        String name = applicant.getQna().get("name").toString();
        String message = """
                안녕하세요. %s님. 에코노베이션입니다.
                
                에코노베이션 %s기 신입모집 %s 결과가 나왔습니다.
                
                결과는 메일을 통해 확인해주시길 바랍니다.
                
                확인하시고 문자 회신 부탁드립니다.
                """;

        String finalMessage = String.format(message, name, year, messageGenerator.documentOrInterview());

        boolean result = smsSender.sendSms(finalMessage, phoneNumber);

        log.info("SMS RESULT : " + result);
    }

}
