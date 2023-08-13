package com.econovation.recruit.api.card.service;

import com.econovation.recruit.api.applicant.usecase.AnswerLoadUseCase;
import com.econovation.recruit.api.card.usecase.BoardLoadUseCase;
import com.econovation.recruit.api.card.usecase.BoardRegisterUseCase;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.BoardRepository;
import com.econovation.recruitdomain.domains.board.domain.CardType;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import com.econovation.recruitdomain.domains.board.domain.Navigation;
import com.econovation.recruitdomain.domains.board.exception.BoardNotFoundException;
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

//    @Override
//    @Transactional(readOnly = true)
//    public Map<String, Integer> getNewestLocationByNavLocAndColLoc(
//            Integer navigationId, Integer colLoc) {
//        List<Board> boards = boardLoadPort.getBoardByNavLavigationIdAndColLoc(navigationId, colLoc);
//
//        Board board =
//                boards.stream()
//                        .max(Comparator.comparing(Board::getNextBoardID))
//                        .orElseThrow(NoSuchFieldError::new);
//
//        Map<String, Integer> newestLocation = new HashMap<>();
//        newestLocation.put("prevLowLoc", board.getPrevLowLoc() + 1);
//        newestLocation.put("nextLowLoc", board.getNextBoardID() + 1);
//        return newestLocation;
//    }
@Override
@Transactional(readOnly = true)
public Map<String, Integer> getNewestLocationByNavLocAndColLoc(Integer navigationId, Integer nextColId) {
    List<Board> boards = boardLoadPort.getBoardByNavLavigationIdAndColLoc(navigationId, nextColId);

    Board board = boards.stream()
//            .max(Comparator.comparing(Board::getNextBoardID))
            .filter(b -> b.getNextBoardID() == null).findFirst()
            .orElseThrow(NoSuchElementException::new);

    Map<String, Integer> newestLocation = new HashMap<>();
    newestLocation.put("nextLowLoc", board.getNextLowLoc() + 1);
    return newestLocation;
}


    @Override
    public Board findById(Integer id) {
        return boardLoadPort.getBoardById(id).getValue();
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
        Integer nextColId = -1;
        if (hopeField.equals("개발자")) {
            nextColId = 1;
        } else if (hopeField.equals("디자이너")) {
            nextColId = 2;
        } else if (hopeField.equals("기획자")) {
            nextColId = 3;
        } else {
            throw InvalidHopeFieldException.EXCEPTION;
        }
        Columns column =
                columnLoadPort.getColumnByPrevColLocAndNextColLocAndNavigationId(
                        nextColId,
                        0);
        Board board =
                Board.builder()
                        .cardType(CardType.APPLICANT)
                        .nextBoardID(null)
                        .columnId(column.getId())
                        .cardId(cardId)
                        .build();
        Board save = boardRecordPort.save(board);
//        기존에 null 인 nextBoardId를 현재 boardId로 업데이트
        boardLoadPort.getBoardByNavLavigationIdAndColLoc(0, nextColId).stream()
                .filter(b -> b.getNextBoardID() == null)
                .findFirst()
                .ifPresent(b -> {
                    b.updateNextBoardID(save.getId());
                });
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
        Columns columns = columnLoadPort.findById(board.getColumnId());
        Integer navigationId = columns.getNavigationId();
        Board destinationBoard = boardLoadPort.getBoardByNavLavigationIdAndColLoc(navigationId, colLoc).stream()
                .filter(board1 -> board1.getPrevLowLoc() == lowLoc - 1 && board1.getNextBoardID() == lowLoc + 1).findFirst().get();
        Columns destinationColumn = columnLoadPort.findById(destinationBoard.getColumnId());

        // 같은 column 에 low 만 다를 경우
        if(board.getColumnId().equals(destinationBoard.getColumnId()) &&
                !(board.getPrevLowLoc().equals(lowLoc-1) && board.getNextBoardID().equals(lowLoc+1))
        ) {
            // 1. low 만 수정한다
            board.updateLocation(lowLoc-1, lowLoc+1);
            destinationBoard.updateLocation(board.getPrevLowLoc(), board.getNextBoardID());
        }
        // 다른 column 에 low 가 같을 경우
        else if(!board.getColum nId().equals(destinationBoard.getColumnId()) &&
                board.getPrevLowLoc().equals(lowLoc-1) && board.getNextBoardID().equals(lowLoc+1)
        ) {
            // 1. column 을 바꾼다.
            columns.updateLocation(colLoc-1, colLoc+1);
            destinationColumn.updateLocation(board.getPrevLowLoc(), board.getNextBoardID());
        }
        // 다른 column 에 low 가 다를 경우
        else if(!board.getColumnId().equals(destinationBoard.getColumnId()) &&
                !(board.getPrevLowLoc().equals(lowLoc-1) && board.getNextBoardID().equals(lowLoc+1))
        ) {
            // 1. column 을 수정한다.
            // 2. low 를 수정한다.
            board.updateLocation(lowLoc-1, lowLoc+1);
            columns.updateLocation(colLoc-1, colLoc+1);
            destinationBoard.updateLocation(board.getPrevLowLoc(), board.getNextBoardID());
            destinationColumn.updateLocation(board.getPrevLowLoc(), board.getNextBoardID());
        }
        // 소켓서버로 전송
        messagingTemplate.convertAndSend("/sub/boards/", );
        return save;
    }

    @Override
    @Transactional
    public void relocateCard(UpdateLocationBoardDto updateLocationBoardDto) {
        //Result find onSuccess -> updateLocation
        // 옮기려는 자리에 카드가 있는지 확인
        Result<Board> boardResult = boardLoadPort.getBoardById(updateLocationBoardDto.getId());
        // 옮기는 자리에 카드가 있으면 그 카드의 위치를 바꿔준다.
        boardResult.onSuccess(
                board -> {
                    updateLocation(board, updateLocationBoardDto.getColLoc(), updateLocationBoardDto.getLowLoc());
                });
        //Result find onFailure -> throw Exception
        boardResult.onFailure(
                throwable -> {
                    throw BoardNotFoundException.EXCEPTION;
                });
            //            소켓서버로 전송
            messagingTemplate.convertAndSend("/sub/boards/", boards);
        }
    }
}
