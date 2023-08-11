package com.econovation.recruit.api.card.service;

import com.econovation.recruit.api.applicant.usecase.AnswerLoadUseCase;
import com.econovation.recruit.api.card.usecase.BoardLoadUseCase;
import com.econovation.recruit.api.card.usecase.BoardRegisterUseCase;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.BoardRepository;
import com.econovation.recruitdomain.domains.board.domain.CardType;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import com.econovation.recruitdomain.domains.board.domain.Navigation;
import com.econovation.recruitdomain.domains.board.exception.InvalidHopeFieldException;
import com.econovation.recruitdomain.domains.dto.UpdateLocationBoardDto;
import com.econovation.recruitdomain.out.BoardLoadPort;
import com.econovation.recruitdomain.out.BoardRecordPort;
import com.econovation.recruitdomain.out.ColumnLoadPort;
import com.econovation.recruitdomain.out.ColumnRecordPort;
import com.econovation.recruitdomain.out.NavigationLoadPort;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService implements BoardLoadUseCase, BoardRegisterUseCase {
    private static final String NO_BOARD_MESSAGE = "해당하는 Board가 없습니다";
    private final NavigationLoadPort navigationLoadPort;
    private final BoardRecordPort boardRecordPort;
    private final BoardLoadPort boardLoadPort;
    private final SimpMessagingTemplate messagingTemplate;
    private final BoardRepository boardRepository;
    private final AnswerLoadUseCase answerLoadUseCase;

    private final ColumnLoadPort columnLoadPort;
    private final ColumnRecordPort columnRecordPort;

    /*    @Override
    public Board save(Map<String, Integer> newestLocation, String hopeField, Integer navLoc) {
                    Navigation navigation = navigationLoadPort.getByNavLoc(navLoc);
                    Board board =
                                                    Board.builder()
                                                                                    .colLoc(newestLocation.get("colLoc"))
                                                                                    .colTitle(hopeField)
                                                                                    .lowLoc(newestLocation.get("lowLoc"))
                                                                                    .navigation(navigation)
                                                                                    .build();
                    return boardRecordPort.save(board);
    }*/

    //    @Override
    //    public Map<String, Integer> getNewestLocation(String hopeField) {
    //        // hopeField 에 일치하는 board List
    //        List<Board> boardsByHopeField = boardLoadPort.getByHopeField(hopeField);
    //        Board board =
    //                boardsByHopeField.stream()
    //                        .max(Comparator.comparing(Board::getLowLoc))
    //                        .orElseThrow(NoSuchFieldError::new);
    //        Map newestLocation = new HashMap();
    //        newestLocation.put("colLoc", board.getColLoc());
    //        newestLocation.put("lowLoc", board.getLowLoc() + 1);
    //        return newestLocation;
    //    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Integer> getNewestLocationByNavLocAndColLoc(
            Integer navigationId, Integer colLoc) {
        List<Board> boards = boardLoadPort.getBoardByNavLavigationIdAndColLoc(navigationId, colLoc);

        Board board =
                boards.stream()
                        .max(Comparator.comparing(Board::getNextLowLoc))
                        .orElseThrow(NoSuchFieldError::new);

        Map<String, Integer> newestLocation = new HashMap<>();
        newestLocation.put("prevLowLoc", board.getPrevLowLoc() + 1);
        newestLocation.put("nextLowLoc", board.getNextLowLoc() + 1);
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
        return false;
    }

    //    @Override
    //    public void lagLowColBelowLocation(Integer navLoc, Integer colLoc, Integer lowLoc) {
    //        List<Board> boards = boardLoadPort.getBoardByNavLocAndColLoc(navLoc, colLoc);
    //        boardRecordPort.lagUpdateAll(boards);
    //    }

    @Override
    public Board createWorkBoard(Integer navigationId, Integer colLoc, Integer cardId) {
        Map<String, Integer> newestLocation =
                getNewestLocationByNavLocAndColLoc(navigationId, colLoc);
        List<Columns> columnByNavigationId = columnLoadPort.getColumnByNavigationId(navigationId);
        Integer prevColLoc = colLoc - 1;
        Integer nextColLoc = colLoc + 1;

        Columns column =
                columnLoadPort.getColumnByPrevColLocAndNextColLocAndNavigationId(
                        prevColLoc, nextColLoc, navigationId);
        Board board =
                Board.builder()
                        .cardType(CardType.WORK_CARD)
                        .nextLowLoc(newestLocation.get("nextLowLoc"))
                        .prevLowLoc(newestLocation.get("prevLowLoc"))
                        .columnId(column.getId())
                        .cardId(cardId)
                        .build();
        // TODO : 소켓서버로 전송 차후에 주석 제거 예정
        // messagingTemplate.convertAndSend("/sub/boards/message", board);
        return boardRecordPort.save(board);
    }

    @Override
    public void createApplicantBoard(UUID applicantId, String hopeField, Integer cardId) {
        Integer colLoc = -1;
        if (hopeField.equals("개발자")) {
            colLoc = 0;
        } else if (hopeField.equals("디자이너")) {
            colLoc = 1;
        } else if (hopeField.equals("기획자")) {
            colLoc = 2;
        } else {
            throw InvalidHopeFieldException.EXCEPTION;
        }
        Map<String, Integer> newestLocationByNavLocAndColLoc =
                getNewestLocationByNavLocAndColLoc(0, colLoc);
        Columns column =
                columnLoadPort.getColumnByPrevColLocAndNextColLocAndNavigationId(
                        newestLocationByNavLocAndColLoc.get("prevColLoc"),
                        newestLocationByNavLocAndColLoc.get("nextColLoc"),
                        0);

        Board board =
                Board.builder()
                        .cardType(CardType.APPLICANT)
                        .nextLowLoc(newestLocationByNavLocAndColLoc.get("nextLowLoc"))
                        .prevLowLoc(newestLocationByNavLocAndColLoc.get("prevLowLoc"))
                        .columnId(column.getId())
                        .cardId(cardId)
                        .build();
        boardRecordPort.save(board);
    }

    @Override
    public Columns createColumn(String title, Integer navigationId) {
        Columns column = Columns.builder().title(title).navigationId(navigationId).build();
        return columnRecordPort.save(column);
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
        board.update(colLoc, lowLoc);
        Board save = boardRecordPort.save(board);
        // 소켓서버로 전송
        messagingTemplate.convertAndSend("/sub/boards/", save);
        return save;
    }

    @Override
    @Transactional
    public void relocationBetweenStartToEndLowLoc(UpdateLocationBoardDto updateLocationBoardDto) {
        // 중복되지 않은 경우에  조회를 하려는 것 자체가 문제야
        // 현재 board의 위치
        Board board = findById(updateLocationBoardDto.getId());
        Integer destinationLowLoc = updateLocationBoardDto.getLowLoc();
        Integer startLowLoc = board.getLowLoc();
        List<Board> boards =
                new ArrayList<>(
                        boardLoadPort.getBoardBetweenLowLoc(startLowLoc, destinationLowLoc));
        // 위로 올리는 경우
        if (startLowLoc < destinationLowLoc) {
            board.setLowLoc(destinationLowLoc);
            boardRecordPort.save(board);
            boards.remove(0);
            for (Board b : boards) {
                b.setLowLoc(b.getLowLoc() - 1);
            }
            boardRecordPort.batchUpdate(boards);
        } else if (startLowLoc == destinationLowLoc) {
            throw new IllegalArgumentException("같은 위치는 이동할 수 없습니다.");
        } else {
            board.setLowLoc(destinationLowLoc);
            boardRecordPort.save(board);
            log.info(String.valueOf(boards.size()));
            boards.remove(boards.size() - 1);
            for (Board b : boards) {
                b.setLowLoc(b.getLowLoc() + 1);
                log.info(
                        b.getColTitle()
                                + " , "
                                + b.getLowLoc()
                                + " , "
                                + b.getColTitle()
                                + " , "
                                + b.getColLoc()
                                + " , "
                                + b.getLowLoc());
            }
            boardRepository.saveAll(boards);
            //            소켓서버로 전송
            messagingTemplate.convertAndSend("/sub/boards/", boards);
        }
    }
}
