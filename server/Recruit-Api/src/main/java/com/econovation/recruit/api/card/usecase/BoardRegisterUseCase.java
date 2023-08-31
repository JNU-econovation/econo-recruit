package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import com.econovation.recruitdomain.domains.dto.UpdateLocationBoardDto;

@UseCase
public interface BoardRegisterUseCase {
    void execute(Board board);

    Board createWorkBoard(Integer columnId, Long cardId);

    void createApplicantBoard(String applicantId, String hopeField, Long cardId);

    Columns createColumn(String title, Integer navigationId);
    Board updateLocation(Board board, Integer colLoc, Integer lowLoc);

    void relocateCard(UpdateLocationBoardDto updateLocationBoardDto);
}
