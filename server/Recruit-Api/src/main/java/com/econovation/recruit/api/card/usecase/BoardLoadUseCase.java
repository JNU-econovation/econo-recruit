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
    //    boolean isDuplicateLocation(Integer navLoc, Integer colLoc, Integer lowLoc);
    List<Board> findAllByNavLoc(Integer navLoc);

    List<Navigation> getAllNavigation();

    Navigation getNavigationByNavLoc(Integer navLoc);

    List<Board> getBoardByColumnsIds(List<Integer> columnsIds);

    //    Navigation getByNavLoc(Integer navLoc);

}
