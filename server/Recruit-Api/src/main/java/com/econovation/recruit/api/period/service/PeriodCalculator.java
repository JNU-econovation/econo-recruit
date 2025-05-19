package com.econovation.recruit.api.period.service;

import com.econovation.recruitdomain.domains.applicant.domain.state.PeriodStates;
import com.econovation.recruitdomain.domains.period.domain.Period;
import com.econovation.recruitdomain.out.PeriodLoadPort;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PeriodCalculator {

    private final PeriodProvider periodProvider;

    @Value("${econovation.recruit.period.firstDiscussion}")
    private String firstDiscussionPeriod;

    @Value("${econovation.recruit.period.finalDiscussion}")
    private String finalDiscussionPeriod;

    public PeriodStates execute() {
        Period period = periodProvider.get();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstDiscussionEnd = period.getFirstDiscussionEnd();
        LocalDateTime finalDiscussionEnd = period.getFinalDiscussionEnd();;

        // 설정된 기간과 현재 시간으로 어떤 기간인지 판별하기
        if (now.isBefore(firstDiscussionEnd))
            return PeriodStates.FIRST_DISCUSSION;
        else if (now.isBefore(finalDiscussionEnd))
            return PeriodStates.FINAL_DISCUSSION;
        else
            return PeriodStates.END;
    }
}
