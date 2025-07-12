package com.econovation.recruit.api.recruitment.util;

import com.econovation.recruitdomain.common.aop.redissonLock.RedissonLock;
import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


public class LatestRecruitmentVo {

    private Integer year;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private RecruitmentStates state;

    public LatestRecruitmentVo(Recruitment recruitment){
        if(Objects.nonNull(recruitment)){
            this.startAt = recruitment.getStartAt();
            this.endAt = recruitment.getEndAt();
            this.state = recruitment.getStates();
            this.year = recruitment.getYear();
        }
    }

    // 파라미터로 넘긴 recruitment 객체의 id로 lock 을 할당 받는다.
    @RedissonLock(LockName = "모집 정보 최신화", identifier = "id", paramClassType = Recruitment.class)
    public void refreshRecruitment(Recruitment recruitment) {
        if(Objects.nonNull(recruitment)){
            this.startAt = recruitment.getStartAt();
            this.endAt = recruitment.getEndAt();
            this.state = recruitment.getStates();
            this.year = recruitment.getYear();
            return;
        }
        throw new IllegalArgumentException("최신 모집이 등록되지 않았습니다.");
    }

    public Integer getYear() {
        validate();
        return this.year;
    }

    public LocalDateTime getStartAt() {
        validate();
        return this.startAt;
    }

    public LocalDateTime getEndAt() {
        validate();
        return this.endAt;
    }

    public RecruitmentStates getState(){
        validate();
        return this.state;
    }

    private void validate(){
        if (Objects.nonNull(year) || Objects.nonNull(startAt) || Objects.nonNull(endAt) || Objects.nonNull(state))
            throw new IllegalArgumentException("최신 모집이 등록되지 않았습니다.");
    }
}
