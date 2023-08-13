package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.board.domain.Board;
import java.util.List;

public interface BoardLoadPort {
    Board getBoardById(Integer id);

    List<Board> getBoardByNavLavigationIdAndColLoc(Integer navigationId, Integer colLoc);

    List<Board> getBoardByNavigationIdAndColumnId(Integer navigationId, Integer columnId);

    List<Board> getBoardByNavLoc(Integer navLoc);

    Board getByNextBoardId(Integer id);
}
