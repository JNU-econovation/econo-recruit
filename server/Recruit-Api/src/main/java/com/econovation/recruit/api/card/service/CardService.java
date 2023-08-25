package com.econovation.recruit.api.card.service;

import com.econovation.recruit.api.card.usecase.BoardLoadUseCase;
import com.econovation.recruit.api.card.usecase.BoardRegisterUseCase;
import com.econovation.recruit.api.card.usecase.CardLoadUseCase;
import com.econovation.recruit.api.card.usecase.CardRegisterUseCase;
import com.econovation.recruit.api.card.usecase.ColumnsUseCase;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import com.econovation.recruitdomain.domains.board.dto.ColumnsResponseDto;
import com.econovation.recruitdomain.domains.card.domain.Card;
import com.econovation.recruitdomain.domains.card.dto.CardResponseDto;
import com.econovation.recruitdomain.domains.dto.CreateWorkCardDto;
import com.econovation.recruitdomain.out.CardLoadPort;
import com.econovation.recruitdomain.out.CardRecordPort;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService implements CardRegisterUseCase, CardLoadUseCase {
    private final CardRecordPort cardRecordPort;
    private final CardLoadPort cardLoadPort;
    private final BoardRegisterUseCase boardRegisterUseCase;
    private final BoardLoadUseCase boardLoadUseCase;
    private final ColumnsUseCase columnsUseCase;

    /*    @Override
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
    }*/

    @Override
    @Transactional(readOnly = true)
    public List<Card> findAll() {
        return cardLoadPort.findAll();
    }

    @Override
    public List<Map<ColumnsResponseDto, CardResponseDto>> getByNavigationId(Integer navigationId) {
        List<Columns> columns = columnsUseCase.getByNavigationId(navigationId);
        List<ColumnsResponseDto> columnsResponseDtos =
                columns.stream().map(ColumnsResponseDto::from).collect(Collectors.toList());

        List<Integer> columnsIds =
                columns.stream().map(Columns::getId).collect(Collectors.toList());

        List<Board> boards = boardLoadUseCase.getBoardByColumnsIds(columnsIds);
        List<Card> cards = cardLoadPort.findByIdIn(
                boards.stream().map(Board::getCardId).collect(Collectors.toList())
        );

        Map<Long, Card> cardByBoardIdMap =
                cards.stream().collect(Collectors.toMap(Card::getId, card -> card));

        List<Map<ColumnsResponseDto, CardResponseDto>> result = new LinkedList<>();

        for (Board board : boards) {
            Card card = cardByBoardIdMap.get(board.getCardId());
            if (card != null) {
                CardResponseDto cardDto = CardResponseDto.from(card, board);

                ColumnsResponseDto columnsResponseDto =
                        columnsResponseDtos.stream()
                                .filter(dto -> dto.getColumnsId().equals(board.getColumnId()))
                                .findFirst()
                                .orElse(null);

                if (columnsResponseDto != null) {
                    result.add(Map.of(columnsResponseDto, cardDto));
                }
            }
        }

        return result;
    }

    @Override
    @Transactional
    public void deleteById(Long cardId) {
        // 본인 댓글만 삭제할 수 있다.
        cardRecordPort.delete(cardId);
    }

    @Override
    @Transactional
    public void saveWorkCard(CreateWorkCardDto createWorkCardDto) {
        Card card =
                Card.builder()
                        .title(createWorkCardDto.getTitle())
                        .content(createWorkCardDto.getContent())
                        .build();
        Card savedCard = cardRecordPort.save(card);
        boardRegisterUseCase.createWorkBoard(createWorkCardDto.getColumnId(), savedCard.getId());
    }
}
