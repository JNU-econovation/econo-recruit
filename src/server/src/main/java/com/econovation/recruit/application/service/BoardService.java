package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.BoardUseCase;
import com.econovation.recruit.application.port.out.BoardLoadPort;
import com.econovation.recruit.application.port.out.BoardRecordPort;
import com.econovation.recruit.application.port.out.NavigationLoadPort;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.board.Navigation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService implements BoardUseCase {
    private final NavigationLoadPort navigationLoadPort;
    private final BoardRecordPort boardRecordPort;
    private final BoardLoadPort boardLoadPort;
    @Override
    public Board save(Map<String, Integer> newestLocation, String hopeField, Integer navLoc) {
        Navigation navigation = navigationLoadPort.getByNavLoc(navLoc);
        Board board = Board.builder()
                .colLoc(newestLocation.get("colLoc"))
                .colTitle(hopeField)
                .lowLoc(newestLocation.get("lowLoc"))
                .navigation(navigation)
                .build();
        return boardRecordPort.save(board);
    }

    @Override
    public Map<String, Integer> getNewestLocation(String hopeField) {
        //hopeField 에 일치하는 board List
        List<Board> boardsByHopeField = boardLoadPort.getByHopeField(hopeField);
        Board board = boardsByHopeField.stream()
                .max(Comparator.comparing(Board::getLowLoc))
                .orElseThrow(NoSuchFieldError::new);
        Map newestLocation = new HashMap();
        newestLocation.put("colLoc", board.getColLoc());
        newestLocation.put("lowLoc", board.getLowLoc() + 1);
        return newestLocation;
    }

    @Override
    public Map<String, Integer> getNewestLocationByNavLocAndColLoc(Integer navLoc, Integer colLoc) {
        List<Board> boards = boardLoadPort.getByNavColAndColLoc(navLoc, colLoc);
        Board board = boards.stream()
                .max(Comparator.comparing(Board::getLowLoc))
                .orElseThrow(NoSuchFieldError::new);
        Map<String, Integer> newestLocation = new HashMap<>();
        newestLocation.put("lowLoc", board.getLowLoc());
        newestLocation.put("colLoc", board.getLowLoc());
        return newestLocation;
    }

    @Override
    public Board findById(Long id) {
        return boardLoadPort.getBoardById(id);
    }

    @Override
    public boolean isDuplicateLocation(Integer navLoc, Integer colLoc, Integer lowLoc) {
        Map<String, Integer> location = new HashMap<>();
        location.put("colLoc", colLoc);
        location.put("lowLoc", lowLoc);
        if(boardLoadPort.getBoardByLocation(navLoc,colLoc,lowLoc).equals(null)){
            return false;
        }
        return true;
    }

    @Override
    public void lagLowColBelowLocation(Integer navLoc, Integer colLoc, Integer lowLoc) {
        List<Board> boards = boardLoadPort.getBoardByNavLocAndColLoc(navLoc, colLoc);
        boardRecordPort.lagUpdateAll(boards);
    }

    @Override
    public Board createWorkBoard(String workContent, Integer navLoc, Integer colLoc, Integer lowLoc) {
        return null;
    }

    @Override
    public List<Board> findAllByNavLoc(Integer navLoc) {
        return boardLoadPort.getBoardByNavLoc(navLoc);

    }
}
