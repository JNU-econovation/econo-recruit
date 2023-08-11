package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.Column;
import java.util.UUID;

@UseCase
public interface BoardRegisterUseCase {
    void execute(Board board);

    Board createWorkBoard(Integer navigationId, Integer colLoc);

    Board createApplicantBoard(UUID applicantId);

    Column createColumn(String title, Integer navigationId);
    //    void execute(CreateWorkCardDto createWorkCardDto, CardType cardType);
}
