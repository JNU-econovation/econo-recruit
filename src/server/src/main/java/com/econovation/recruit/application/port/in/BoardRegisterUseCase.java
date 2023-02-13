package com.econovation.recruit.application.port.in;

import com.econovation.recruit.domain.board.Board;

import java.util.Map;

public interface BoardRegisterUseCase {
    Board save(Map<String, Integer> newestLocation, String hopeField, Integer navLoc);
}
