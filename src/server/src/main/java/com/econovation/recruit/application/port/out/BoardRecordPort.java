package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.board.Board;

import java.util.List;

public interface BoardRecordPort {
    Board save(Board board);

    List<Board> lagUpdateAll(List<Board> boards);
}
