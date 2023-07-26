package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.board.Board;
import java.util.List;

public interface BoardRecordPort {

    Board save(Board board);

    List<Board> lagUpdateAll(List<Board> boards);

    void batchUpdate(List<Board> boards);

    void delete(Board board);

    //    void update(Board board);
}
