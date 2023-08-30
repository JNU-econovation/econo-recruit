package com.econovation.recruit.api.card.service;

import static com.econovation.recruitcommon.consts.RecruitStatic.*;

import com.econovation.recruit.api.applicant.usecase.AnswerLoadUseCase;
import com.econovation.recruit.api.card.usecase.BoardLoadUseCase;
import com.econovation.recruit.api.card.usecase.BoardRegisterUseCase;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.BoardRepository;
import com.econovation.recruitdomain.domains.board.domain.CardType;
import com.econovation.recruitdomain.domains.board.domain.Columns;
import com.econovation.recruitdomain.domains.board.domain.Navigation;
import com.econovation.recruitdomain.domains.board.dto.ColumnsResponseDto;
import com.econovation.recruitdomain.domains.board.exception.InvalidHopeFieldException;
import com.econovation.recruitdomain.domains.dto.UpdateLocationBoardDto;
import com.econovation.recruitdomain.out.BoardLoadPort;
import com.econovation.recruitdomain.out.BoardRecordPort;
import com.econovation.recruitdomain.out.ColumnLoadPort;
import com.econovation.recruitdomain.out.ColumnRecordPort;
import com.econovation.recruitdomain.out.NavigationLoadPort;
import java.util.List;
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
    //        List<Board> boards = boardLoadPort.getBoardByNavLavigationIdAndColLoc(navigationId,
    // colLoc);
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
    /*@Override
    @Transactional(readOnly = true)
    public Map<String, Integer> getNewestLocationByNavLocAndColLoc(Integer navigationId, Integer nextColId) {
        List<Board> boards = boardLoadPort.getBoardByNavLavigationIdAndColLoc(navigationId, nextColId);

        Board board = boards.stream()
    //            .max(Comparator.comparing(Board::getNextBoardID))
                .filter(b -> b.getNextBoardId() == null).findFirst()
                .orElseThrow(BoardNotFoundException.EXCEPTION);

        Map<String, Integer> newestLocation = new HashMap<>();
        newestLocation.put("nextLowLoc", board.getNextLowLoc() + 1);
        return newestLocation;
    }*/

    @Override
    public Board findById(Integer id) {
        return boardLoadPort.getBoardById(id);
    }

    @Override
    public void execute(Board board) {
        boardRecordPort.save(board);
    }

    @Override
    public Board createWorkBoard(Integer columnId, Long cardId) {
        Columns column = columnLoadPort.findById(columnId);
        List<Board> boardByNavigationIdAndColumnId =
                boardLoadPort.getBoardByNavigationIdAndColumnId(1, columnId);
        Board board =
                boardRecordPort.save(
                        Board.builder()
                                .cardType(CardType.WORK_CARD)
                                .nextBoardId(null)
                                .columnId(column.getId())
                                .cardId(cardId)
                                .navigationId(column.getNavigationId())
                                .build());
        //        기존에 null 인 nextBoardId를 현재 boardId로 업데이트
        if (!boardByNavigationIdAndColumnId.isEmpty()) {
            boardByNavigationIdAndColumnId.stream()
                    .filter(b -> b.getNextBoardId() == null)
                    .findFirst()
                    .ifPresent(
                            b -> {
                                b.updateNextBoardID(board.getId());
                            });
        }
        // board 에서 null
        // TODO : 소켓서버로 전송 차후에 주석 제거 예정
        // messagingTemplate.convertAndSend("/sub/boards/message", board);
        return board;
    }

    /*    @Override
    @Transactional
    public Columns createColumn(String title, Integer navigationId) {
        Columns column = Columns.builder()
                .title(title)
                .navigationId(navigationId)
                .build();

        List<Columns> columnsByNavigationId = columnLoadPort.getColumnsByNavigationId(navigationId);
        Columns save = columnRecordPort.save(column);

        if (!columnsByNavigationId.isEmpty()) {
            columnsByNavigationId.stream()
                    .filter(c -> c.getNextColumnsId() == null)
                    .findFirst()
                    .ifPresent(c -> c.updateNextColumnsId(save.getId()));
        }

        return save;
    }*/

    @Override
    public void createApplicantBoard(String applicantId, String hopeField, Long cardId) {
        Integer columnsId = -1;
        if (hopeField.equals("개발자")) {
            columnsId = DEVELOPER_COLUMNS_ID;
        } else if (hopeField.equals("디자이너")) {
            columnsId = DESIGNER_COLUMNS_ID;
        } else if (hopeField.equals("기획자")) {
            columnsId = PLANNER_COLUMNS_ID;
        } else {
            throw InvalidHopeFieldException.EXCEPTION;
        }

        Columns column = columnLoadPort.getColumnByNextColumnsId(columnsId);
        Board board =
                Board.builder()
                        .cardType(CardType.APPLICANT)
                        .nextBoardId(null)
                        .columnId(column.getId())
                        .cardId(cardId)
                        .build();
        Board save = boardRecordPort.save(board);
        //        기존에 null 인 nextBoardId를 현재 boardId로 업데이트
        boardLoadPort.getBoardByNavigationIdAndColumnsId(0, columnsId).stream()
                .filter(b -> b.getNextBoardId() == null)
                .findFirst()
                .ifPresent(
                        b -> {
                            b.updateNextBoardID(save.getId());
                        });
    }

    @Override
    @Transactional
    public Columns createColumn(String title, Integer navigationId) {
        Columns column = Columns.builder().title(title).navigationId(navigationId).build();

        List<Columns> columnsByNavigationId = columnLoadPort.getColumnsByNavigationId(navigationId);
        Columns save = columnRecordPort.save(column);

        if (!columnsByNavigationId.isEmpty()) {
            columnsByNavigationId.stream()
                    .filter(c -> c.getNextColumnsId() == null)
                    .findFirst()
                    .ifPresent(c -> c.updateNextColumnsId(save.getId()));
        }

        return save;
    }

    @Override
    public Board updateLocation(Board board, Integer colLoc, Integer lowLoc) {
        return null;
    }

    @Override
    public List<Board> findAllByNavigationId(Integer navigationId) {
        return boardLoadPort.getBoardByNavLoc(navigationId);
    }

    @Override
    public List<Navigation> getAllNavigation() {
        return navigationLoadPort.getAllNavigation();
    }

    @Override
    public Navigation getNavigationByNavLoc(Integer navLoc) {
        return navigationLoadPort.getByNavigationId(navLoc);
    }

    @Override
    public List<Board> getBoardByColumnsIds(List<Integer> columnsIds) {
        return boardLoadPort.getBoardByColumnsIds(columnsIds);
    }

    @Override
    public List<ColumnsResponseDto> getColumnsByNavigationId(Integer navigationId) {
        List<Columns> columns = columnLoadPort.getColumnsByNavigationId(navigationId);
        return ColumnsResponseDto.from(columns);
    }

    //    @Override
    //    public Board updateLocation(Board board, Integer targetBoardId) {
    //        Columns columns = columnLoadPort.findById(board.getColumnId());
    //        Integer navigationId = columns.getNavigationId();
    //        Board destinationBoard =
    // boardLoadPort.getBoardByNavLavigationIdAndColLoc(navigationId, colLoc).stream()
    //                .filter(board1 -> board1.getPrevLowLoc() == lowLoc - 1 &&
    // board1.getNextBoardId() == lowLoc + 1).findFirst().get();
    //        Columns destinationColumn = columnLoadPort.findById(destinationBoard.getColumnId());
    //
    //        // 같은 column 에 low 만 다를 경우
    //        if(board.getColumnId().equals(destinationBoard.getColumnId()) &&
    //                !(board.getPrevLowLoc().equals(lowLoc-1) &&
    // board.getNextBoardId().equals(lowLoc+1))
    //        ) {
    //            // 1. low 만 수정한다
    //            board.updateLocation(lowLoc-1, lowLoc+1);
    //            destinationBoard.updateLocation(board.getPrevLowLoc(), board.getNextBoardId());
    //        }
    //        // 다른 column 에 low 가 같을 경우
    //        else if(!board.getColum nId().equals(destinationBoard.getColumnId()) &&
    //                board.getPrevLowLoc().equals(lowLoc-1) &&
    // board.getNextBoardId().equals(lowLoc+1)
    //        ) {
    //            // 1. column 을 바꾼다.
    //            columns.updateLocation(colLoc-1, colLoc+1);
    //            destinationColumn.updateLocation(board.getPrevLowLoc(), board.getNextBoardId());
    //        }
    //        // 다른 column 에 low 가 다를 경우
    //        else if(!board.getColumnId().equals(destinationBoard.getColumnId()) &&
    //                !(board.getPrevLowLoc().equals(lowLoc-1) &&
    // board.getNextBoardId().equals(lowLoc+1))
    //        ) {
    //            // 1. column 을 수정한다.
    //            // 2. low 를 수정한다.
    //            board.updateLocation(lowLoc-1, lowLoc+1);
    //            columns.updateLocation(colLoc-1, colLoc+1);
    //            destinationBoard.updateLocation(board.getPrevLowLoc(), board.getNextBoardId());
    //            destinationColumn.updateLocation(board.getPrevLowLoc(), board.getNextBoardId());
    //        }
    //        // 소켓서버로 전송
    //        messagingTemplate.convertAndSend("/sub/boards/", );
    //        return save;
    //        return null;
    //    }

    @Override
    @Transactional
    //    @RedissonLock(LockName = "지원서",
    //            paramClassType = UpdateLocationBoardDto.class,
    //            identifier = "boardId",
    //            needSameTransaction = true)
    public void relocateCard(UpdateLocationBoardDto updateLocationBoardDto) {
        Board currentBoard = boardLoadPort.getBoardById(updateLocationBoardDto.getBoardId());
        Board targetBoard = boardLoadPort.getBoardById(updateLocationBoardDto.getTargetBoardId());

        // Update nextBoardId
        updateNextBoardIds(currentBoard, targetBoard);

        // TODO: Send data to the socket server
        // messagingTemplate.convertAndSend("/sub/boards/", boards);
    }

    private void updateNextBoardIds(Board board1, Board board2) {
        Board board1Next = boardLoadPort.getByNextBoardId(board1.getId());
        Board board2Next = boardLoadPort.getByNextBoardId(board2.getId());

        // Update nextBoardId references
        if (board2.getNextBoardId() != null) {
            board2.updateNextBoardID(board1.getNextBoardId());
        }

        if (board1.getNextBoardId() != null) {
            board1.updateNextBoardID(board2.getId());
        }

        if (board1Next != null && board2.getNextBoardId() != null) {
            board1Next.updateNextBoardID(board2.getId());
        }

        if (board2Next != null && board1.getNextBoardId() != null) {
            board2Next.updateNextBoardID(board1.getId());
        }
    }

    /*
    @Override
    public boolean isDuplicateLocation(Integer navLoc, Integer columnId, Integer boardId) {
        Map<String, Integer> location = new HashMap<>();
        location.put("columnId", columnId);
        location.put("boardId", boardId);
        Board boardByLocation = boardLoadPort.getBoardByLocation(navLoc, colLoc, boardId);
        return false;
    }
    */
}
