package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.Navigation;
import com.econovation.recruitdomain.domains.dto.UpdateLocationBoardDto;
import java.util.List;
import java.util.Map;

@UseCase
public interface BoardLoadUseCase {
    Board save(Map<String, Integer> newestLocation, String hopeField, Integer navLoc);

    Map<String, Integer> getNewestLocation(String hopeField);

    Map<String, Integer> getNewestLocationByNavLocAndColLoc(Integer navLoc, Integer colLoc);

    Board findById(Integer id);

    boolean isDuplicateLocation(Integer navLoc, Integer colLoc, Integer lowLoc);

    void lagLowColBelowLocation(Integer navLoc, Integer colLoc, Integer lowLoc);

    List<Board> findAllByNavLoc(Integer navLoc);

    List<Navigation> getAllNavigation();

    Navigation getNavigationByNavLoc(Integer navLoc);

    Board updateLocation(Board board, Integer colLoc, Integer lowLoc);

    void relocationBetweenStartToEndLowLoc(UpdateLocationBoardDto updateLocationBoardDto);

    //    Navigation getByNavLoc(Integer navLoc);

}
