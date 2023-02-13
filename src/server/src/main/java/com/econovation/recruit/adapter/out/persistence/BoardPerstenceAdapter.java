package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.BoardLoadPort;
import com.econovation.recruit.application.port.out.BoardRecordPort;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class BoardPerstenceAdapter implements BoardLoadPort, BoardRecordPort {
    private static final String NO_NAVLOC_MESSAGE = "해당하는 메뉴가 존재하지 않습니다";
    private final BoardRepository boardRepository;
    @Override
    public Map<String, Integer> getNewestLocation(String hopeField) {
        //hopeField 에 일치하는 board List
        List<Board> boardsByHopeField = boardRepository.getByHopeField(hopeField);
        Board board = boardsByHopeField.stream()
                .max(Comparator.comparing(Board::getLowLoc))
                .orElseThrow(NoSuchFieldError::new);
        Map newestLocation = new HashMap();
        newestLocation.put("colLoc", board.getColLoc());
        newestLocation.put("lowLoc", board.getLowLoc() + 1);
        return newestLocation;
    }

    @Override
    public Board getBoardByLocation(Map<String, Integer> newestLocation, Integer navLoc) {
        return null;
    }

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }
//    @Override
//    public Board getColLocByLocation(Map<String, Integer> newestLocation, Integer navLoc) {
//        Navigation navigation = navigationRepository.findNavigationByNavLoc(navLoc)
//                .orElseThrow(() -> new IllegalStateException(NO_NAVLOC_MESSAGE));
//
//        Board.builder().navigation()
//                .lowLoc()
//                .colLoc()
//                .colTitle()
//
//    }

}
