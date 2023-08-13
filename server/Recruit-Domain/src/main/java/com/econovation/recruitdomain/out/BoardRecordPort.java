package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.board.domain.Board;

public interface BoardRecordPort {
    Board save(Board board);

    void delete(Board board);

    //    List<Board> lagUpdateAll(List<Board> boards);
    //
    //    void batchUpdate(List<Board> boards);
}
