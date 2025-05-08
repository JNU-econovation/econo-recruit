package com.econovation.recruit.api.period.service;

import com.econovation.recruitdomain.domains.period.domain.Period;
import com.econovation.recruitdomain.out.PeriodLoadPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PeriodProvider {

    private final PeriodLoadPort periodLoadPort;

    // 최신 데이터가 없을 경우
    // 1. 예외 던지기 -> 예외를 던지면, 이 메소드를 호출하는 쪽에서 문제임
    // 2. 기본값 정해서 기본값 반환하기 -> 기본값을 어떻게 정의할지가 문제
    // 3. env 에 설정된 값 반환하기 -> 환경변수를 잘못 관리하면, 좋지 않음
    public Period get() {
        Optional<Period> period = periodLoadPort.findLatestOne();

        if (period.isEmpty()) {
            // 데이터베이스에 최신의 데이터가 없을 경우 처리
            return null;
        }

        return period.get();
    }
}
