package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.Navigation;
import com.econovation.recruitdomain.domains.board.dto.ColumnsResponseDto;
import java.util.List;

@UseCase
public interface BoardLoadUseCase {

    Board findById(Integer id);

    List<Board> findAllByNavigationId(Integer navigationId);

    List<Navigation> getAllNavigation();

    Navigation getNavigationByNavLoc(Integer navLoc);

    List<Board> getBoardByColumnsIds(List<Integer> columnsIds);

    List<ColumnsResponseDto> getColumnsByNavigationId(Integer navigationId);

    Board getBoardByCardId(Long cardId);

    Result<Board> getBoardByNextBoardId(Integer boardId);

    //    Navigation getByNavLoc(Integer navLoc);

}
