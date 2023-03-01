package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.BoardUseCase;
import com.econovation.recruit.application.port.in.CardRegisterUseCase;
import com.econovation.recruit.application.port.out.BoardRecordPort;
import com.econovation.recruit.application.port.out.CardLoadPort;
import com.econovation.recruit.application.port.out.CardRecordPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.card.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CardService implements CardRegisterUseCase {
    private final BoardUseCase boardUseCase;
    private final CardRecordPort cardRecordPort;
    private final CardLoadPort cardLoadPort;
    private final BoardRecordPort boardRecordPort;
    @Override
    public Card saveApplicantCard(Applicant applicant) {
        // 지원자 희망 분야 (hope_field) 와 매칭되는  col_loc 조회 ( 새로 들어갈 빈 자리 )
        Map<String, Integer> newestLocation = boardUseCase.getNewestLocation(applicant.getHopeField());
        // 그 col_loc 에서 생성될 마지막 low_col 조회
        Board board = boardUseCase.save(newestLocation, applicant.getHopeField(),0);
        return cardRecordPort.save(new Card(applicant, board));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> findAll() {
        return cardLoadPort.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer cardId) {
        Card card = cardLoadPort.findById(cardId);
        boardRecordPort.delete(card.getBoard());
        cardRecordPort.delete(cardId);
    }
}
