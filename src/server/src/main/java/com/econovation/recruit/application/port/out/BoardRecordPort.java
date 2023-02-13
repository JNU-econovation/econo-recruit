package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.board.Board;

import java.util.Map;

public interface BoardRecordPort {
    Board save(Board board);
}
