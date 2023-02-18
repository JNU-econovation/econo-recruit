package com.econovation.recruit.application.port.in;

import com.econovation.recruit.domain.board.Board;

import java.util.List;
import java.util.Map;

public interface BoardUseCase {
    Board save(Map<String, Integer> newestLocation, String hopeField, Integer navLoc);

    Map<String, Integer> getNewestLocation(String hopeField);

    Map<String, Integer> getNewestLocationByNavLocAndColLoc(Integer navLoc, Integer colLoc);

    Board findById(Long id);

    boolean isDuplicateLocation(Integer navLoc, Integer colLoc, Integer lowLoc);

    void lagLowColBelowLocation(Integer navLoc, Integer colLoc, Integer lowLoc);

    Board createWorkBoard(String workContent, Integer navLoc, Integer colLoc, Integer lowLoc);

    List<Board> findAllByNavLoc(Integer navLoc);
}
