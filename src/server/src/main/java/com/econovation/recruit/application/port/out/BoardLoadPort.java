package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.board.Board;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BoardLoadPort {
    List<Board> getByHopeField(String hopeField);

    Board getBoardByLocation(Integer navLoc, Integer colLoc, Integer lowLoc);

    Board getBoardById(Long id);

    List<Board> getBoardByNavLocAndColLoc(Integer navLoc, Integer colLoc);

    List<Board> getByNavColAndColLoc(Integer navLoc, Integer colLoc);

    List<Board> getBoardByNavLoc(Integer navLoc);
}
