package com.econovation.recruit.api.sms.helper;

import com.econovation.recruit.api.applicant.state.support.PeriodCalculator;
import com.econovation.recruitdomain.domains.applicant.domain.state.PeriodStates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SmsMessageGenerator {

    private final PeriodCalculator periodCalculator;

    public String documentOrInterview() {
        PeriodStates periodStates = periodCalculator.execute();

        switch (periodStates) {
            case FINAL_DISCUSSION -> {
                return "서류";
            }
            case END -> {
                return "최종";
            }
            default -> {
                return "";
            }
        }
    }
}
