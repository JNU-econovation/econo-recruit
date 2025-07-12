package com.econovation.recruit.api.config;

import com.econovation.recruit.api.recruitment.quartz.RecruitmentScheduler;
import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.exception.RecruitmentNotFoundException;
import com.econovation.recruitdomain.out.RecruitmentPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RecruitmentConfig {

    private final RecruitmentPort recruitmentPort;
    private final RecruitmentScheduler recruitmentScheduler;

    @Bean
    public LatestRecruitmentVo latestRecruitmentVo(){
        try {
            Recruitment latest = recruitmentPort.findLatestOne()
                    .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

            return new LatestRecruitmentVo(latest);
        } catch (Exception e){
            log.warn("최신 모집 정보가 존재하지 않음");
            return new LatestRecruitmentVo(null);
        }
    }

}
