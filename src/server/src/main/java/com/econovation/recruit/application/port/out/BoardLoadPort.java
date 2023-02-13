package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.board.Board;

import java.util.Map;

public interface BoardLoadPort {
    Map<String, Integer> getNewestLocation(String hopeField);

    Board getBoardByLocation(Map<String, Integer> newestLocation, Integer navLoc);
}
