package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.board.domain.Board;

@UseCase
public interface BoardRegisterUseCase {
    void execute(Board board);

    Board createWorkBoard(Integer navigationId, Integer colLoc);
    //    void execute(CreateWorkCardDto createWorkCardDto, CardType cardType);
}
