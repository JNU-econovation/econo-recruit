package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.board.domain.Board;
import java.util.List;

public interface BoardRecordPort {
    Board save(Board board);

    void delete(Board board);

    void saveAll(List<Board> board);
}
