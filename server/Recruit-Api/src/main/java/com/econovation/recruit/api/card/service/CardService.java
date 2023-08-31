package com.econovation.recruit.api.card.service;

import com.econovation.recruit.api.applicant.usecase.AnswerLoadUseCase;
import com.econovation.recruit.api.card.usecase.BoardLoadUseCase;
import com.econovation.recruit.api.card.usecase.BoardRegisterUseCase;
import com.econovation.recruit.api.card.usecase.CardLoadUseCase;
import com.econovation.recruit.api.card.usecase.CardRegisterUseCase;
import com.econovation.recruit.api.card.usecase.ColumnsUseCase;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import com.econovation.recruitdomain.domains.card.domain.Card;
import com.econovation.recruitdomain.domains.card.dto.BoardCardResponseDto;
import com.econovation.recruitdomain.domains.dto.CreateWorkCardDto;
import com.econovation.recruitdomain.domains.label.domain.Label;
import com.econovation.recruitdomain.out.CardLoadPort;
import com.econovation.recruitdomain.out.CardRecordPort;
import com.econovation.recruitdomain.out.LabelLoadPort;
import java.util.HashMap;
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
    private final AnswerLoadUseCase answerLoadPort;
    private final LabelLoadPort labelLoadPort;
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
    @Transactional(readOnly = true)
    public List<BoardCardResponseDto> getByNavigationId(Integer navigationId) {
        List<Columns> columns = columnsUseCase.getByNavigationId(navigationId);

        List<Integer> columnsIds =
                columns.stream().map(Columns::getId).collect(Collectors.toList());

        List<Board> boards = boardLoadUseCase.getBoardByColumnsIds(columnsIds);
        List<Card> cards =
                cardLoadPort.findByIdIn(
                        boards.stream().map(Board::getCardId).collect(Collectors.toList()));

        Map<Long, Card> cardByBoardIdMap =
                cards.stream().collect(Collectors.toMap(Card::getId, card -> card));

        List<BoardCardResponseDto> result = new LinkedList<>();

        // key : applicantId
        Map<String, HashMap<String, String>> answers =
                answerLoadPort.findAllApplicantVo(List.of("field1", "field2"));

        Map<Long, Label> labels =
                labelLoadPort.loadLabelByCardIdIn(
                        cards.stream().map(Card::getId).collect(Collectors.toList()));

        for (Board board : boards) {
            Card card = cardByBoardIdMap.get(board.getCardId());
            String firstPriority = "";
            String secondPriority = "";
            if (answers.isEmpty()) {
                result.add(
                        BoardCardResponseDto.from(
                                card, board, firstPriority, secondPriority, false));
                continue;
            }
            Map<String, String> applicantAnswers = answers.get(card.getApplicantId());
            if (applicantAnswers != null) {
                firstPriority = applicantAnswers.getOrDefault("field1", "");
                secondPriority = applicantAnswers.getOrDefault("field2", "");
            }

            Boolean isLabeled = labels.containsKey(card.getId()) ? true : false;

            result.add(
                    BoardCardResponseDto.from(
                            card, board, firstPriority, secondPriority, isLabeled));
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
