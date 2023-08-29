package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.Navigation;
import java.util.List;

@UseCase
public interface BoardLoadUseCase {

    //    Map<String, Integer> getNewestLocation(String hopeField);
    //    Map<String, Integer> getNewestLocationByNavLocAndColLoc(Integer navLoc, Integer colLoc);
    Board findById(Integer id);
    //    boolean isDuplicateLocation(Integer navigationId, Integer colLoc, Integer lowLoc);
    List<Board> findAllByNavigationId(Integer navigationId);

    List<Navigation> getAllNavigation();

    Navigation getNavigationByNavLoc(Integer navLoc);

    List<Board> getBoardByColumnsIds(List<Integer> columnsIds);

    //    Navigation getByNavLoc(Integer navLoc);

}
