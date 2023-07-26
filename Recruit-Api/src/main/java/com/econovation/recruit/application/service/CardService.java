package com.econovation.recruit.application.service;


import com.econovation.recruit.application.port.in.BoardUseCase;
import com.econovation.recruit.application.port.in.CardRegisterUseCase;
import com.econovation.recruitdomain.out.ApplicantRecordPort;
import com.econovation.recruitdomain.out.BoardRecordPort;
import com.econovation.recruitdomain.out.CardLoadPort;
import com.econovation.recruitdomain.out.CardRecordPort;
import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domain.board.Board;
import com.econovation.recruitdomain.domain.card.Card;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService implements CardRegisterUseCase {
    private final Integer APPLICANT_REGISTER_NAVIGATION_LOCATION = 0;
    private final BoardUseCase boardUseCase;
    private final ApplicantRecordPort applicantRecordPort;
    private final CardRecordPort cardRecordPort;
    private final CardLoadPort cardLoadPort;
    private final BoardRecordPort boardRecordPort;

    @Override
    public Card saveApplicantCard(Applicant applicant) {
        // 지원자 희망 분야 (hope_field) 와 매칭되는  col_loc 조회 ( 새로 들어갈 빈 자리 )
        Map<String, Integer> newestLocation =
                boardUseCase.getNewestLocation(applicant.getHopeField());
        // 그 col_loc 에서 생성될 마지막 low_col 조회
        Board board =
                boardUseCase.save(
                        newestLocation,
                        applicant.getHopeField(),
                        APPLICANT_REGISTER_NAVIGATION_LOCATION);
        Applicant savedApplicant = applicantRecordPort.save(applicant);
        Card card = Card.builder().applicant(savedApplicant).workCardInfo("-").board(board).build();
        return cardRecordPort.save(card);
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
