package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.BoardRegisterUseCase;
import com.econovation.recruit.application.port.in.CardRegisterUseCase;
import com.econovation.recruit.application.port.out.BoardLoadPort;
import com.econovation.recruit.application.port.out.BoardRecordPort;
import com.econovation.recruit.application.port.out.CardRecordPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.card.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CardService implements CardRegisterUseCase {
    private final BoardLoadPort boardLoadPort;
    private final BoardRegisterUseCase boardRegisterUseCase;
    private final CardRecordPort cardRecordPort;
    @Override
    public Card saveApplicantCard(Applicant applicant) {
        // 지원자 희망 분야 (hope_field) 와 매칭되는  col_loc 조회 ( 새로 들어갈 빈 자리 )
        Map<String, Integer> newestLocation = boardLoadPort.getNewestLocation(applicant.getHopeField());
        // 그 col_loc 에서 생성될 마지막 low_col 조회
        Board board = boardRegisterUseCase.save(newestLocation, applicant.getHopeField(),0);
        return cardRecordPort.save(new Card(applicant, board));
    }
}
