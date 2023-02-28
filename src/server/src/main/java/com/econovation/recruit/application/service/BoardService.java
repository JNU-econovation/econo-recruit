package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.BoardUseCase;
import com.econovation.recruit.application.port.out.BoardLoadPort;
import com.econovation.recruit.application.port.out.BoardRecordPort;
import com.econovation.recruit.application.port.out.NavigationLoadPort;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.board.Navigation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService implements BoardUseCase {
    private static final String NO_BOARD_MESSAGE = "해당하는 Board가 없습니다";
    private final NavigationLoadPort navigationLoadPort;
    private final BoardRecordPort boardRecordPort;
    private final BoardLoadPort boardLoadPort;
    private final SimpMessagingTemplate messagingTemplate;

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
    @Transactional(readOnly = true)
    public Map<String, Integer> getNewestLocationByNavLocAndColLoc(Integer navLoc, Integer colLoc) {
        List<Board> boards = boardLoadPort.getByNavColAndColLoc(navLoc, colLoc);
        Board board = boards.stream()
                .max(Comparator.comparing(Board::getLowLoc))
                .orElseThrow(NoSuchFieldError::new);

        log.info(String.valueOf(board.getLowLoc()));

        Map<String, Integer> newestLocation = new HashMap<>();
        newestLocation.put("lowLoc", board.getLowLoc() + 1);
        newestLocation.put("colLoc", board.getColLoc());
        return newestLocation;
    }

    @Override
    public Board findById(Integer id) {
        return boardLoadPort.getBoardById(id);
    }

    @Override
    public boolean isDuplicateLocation(Integer navLoc, Integer colLoc, Integer lowLoc) {
        Map<String, Integer> location = new HashMap<>();
        location.put("colLoc", colLoc);
        location.put("lowLoc", lowLoc);
        Board boardByLocation = boardLoadPort.getBoardByLocation(navLoc, colLoc, lowLoc);
        if(boardByLocation != null){
            log.info("isDuplicated Location By { " + boardByLocation.getLowLoc() + " , " + boardByLocation.getColLoc() + "}");
            return true;
        }
        return false;
    }

    @Override
    public void lagLowColBelowLocation(Integer navLoc, Integer colLoc, Integer lowLoc) {
        List<Board> boards = boardLoadPort.getBoardByNavLocAndColLoc(navLoc, colLoc);
        boardRecordPort.lagUpdateAll(boards);
    }

    @Override
    public Board createWorkBoard(String workContent, Integer navLoc, Integer colLoc, Integer lowLoc) {
        Navigation navigation = navigationLoadPort.getByNavLoc(navLoc);
        Board board = Board.builder()
                .colTitle(workContent)
                .colLoc(colLoc)
                .lowLoc(lowLoc)
                .navigation(navigation)
                .build();
        messagingTemplate.convertAndSend("/sub/boards/message", board);
        return boardRecordPort.save(board);
    }

    @Override
    public List<Board> findAllByNavLoc(Integer navLoc) {
        return boardLoadPort.getBoardByNavLoc(navLoc);
    }

    @Override
    public List<Navigation> getAllNavigation() {
        return navigationLoadPort.getAllNavigation();
    }

    @Override
    public Navigation getNavigationByNavLoc(Integer navLoc) {
        return navigationLoadPort.getByNavLoc(navLoc);
    }

    @Override
    public Board updateLocation(Board board, Integer colLoc, Integer lowLoc) {
        Board update = board.update(colLoc, lowLoc);
        boardRecordPort.save(update);
        messagingTemplate.convertAndSend("/sub/boards/update",update);
        return update;
    }

}
