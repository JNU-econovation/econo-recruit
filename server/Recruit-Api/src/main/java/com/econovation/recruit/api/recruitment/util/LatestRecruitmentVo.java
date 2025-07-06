package com.econovation.recruit.api.recruitment.util;

import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LatestRecruitmentVo{

    private Recruitment latest;

    public Long getYear(){
        if(Objects.nonNull(latest)) return latest.getYear();
        throw new IllegalArgumentException("최신 모집이 등록되지 않았습니다.");
    }

    public LocalDateTime getStartAt(){
        if(Objects.nonNull(latest)) return latest.getStartAt();
        throw new IllegalArgumentException("최신 모집이 등록되지 않았습니다.");
    }

    public LocalDateTime getEndAt(){
        if(Objects.nonNull(latest)) return latest.getEndAt();
        throw new IllegalArgumentException("최신 모집이 등록되지 않았습니다.");
    }

    public void refreshRecruitment(Recruitment recruitment){
        this.latest = recruitment;
    }
}
