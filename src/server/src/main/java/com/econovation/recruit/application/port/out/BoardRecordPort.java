package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.card.Card;

import java.util.List;

public interface BoardRecordPort {

    Board save(Board board);

    List<Board> lagUpdateAll(List<Board> boards);

    void batchUpdate(List<Board> boards);

    void delete(Board board);

//    void update(Board board);
}
