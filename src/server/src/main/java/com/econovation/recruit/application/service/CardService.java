package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.out.CardLoadPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.card.Card;
import org.springframework.stereotype.Service;

@Service
public class CardService implements CardRegisterUseCase{
    private final CardLoadPort cardLoadPort;
    @Override
    public Card saveApplicantCard(Applicant applicant) {
        // 최신 Board location 생성
        // 지원자 희망 분야 (hope_field) 와 매칭되는  col_loc 조회
        getNewestLocation(applicant.getHopeField());
        // 그 col_loc 에서 생성될 마지막 low_col 조회
        // 조회된 low_col, col_loc 와 col_loc 과 매칭되는  col_title 설정
        // default 는 공통 navigation인 1번 인덱스에 맞는 navigation proxy를 생성
        // navigation을 board 에 set
        // 생성된 board를 Card 에 set
        Card card = new Card(applicantId, board);
        return null;
    }
}
