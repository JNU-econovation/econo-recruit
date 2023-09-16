package com.econovation.recruitdomain.domains.board.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.BoardRepository;
import com.econovation.recruitdomain.domains.board.exception.BoardNotFoundException;
import com.econovation.recruitdomain.out.BoardLoadPort;
import com.econovation.recruitdomain.out.BoardRecordPort;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@Adaptor
@RequiredArgsConstructor
public class BoardAdaptor implements BoardLoadPort, BoardRecordPort {
    private static final String NO_NAVLOC_MESSAGE = "해당하는 메뉴가 존재하지 않습니다";
    private static final String NO_BOARD_MESSAGE = "해당하는 BOARD가 존재하지 않습니다";
    private static final String NOT_MATCH_MESSAGE = "일치하는 Board를 찾을 수 없습니다";
    private final BoardRepository boardRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Board getBoardById(Integer id) {
        return boardRepository.findById(id).orElseThrow(() -> BoardNotFoundException.EXCEPTION);
    }

    @Override
    public List<Board> getBoardByNavigationIdAndColumnsId(Integer navigationId, Integer columnsId) {
        return boardRepository.findByNavigationIdAndColumnId(navigationId, columnsId);
    }

    @Override
    public List<Board> getBoardByNavigationIdAndColumnId(Integer navigationId, Integer columnId) {
        return boardRepository.findByNavigationIdAndColumnId(navigationId, columnId);
    }

    @Override
    public List<Board> getBoardByNavLoc(Integer navigationId) {
        return boardRepository.findByNavigationId(navigationId);
    }

    @Override
    public Optional<Board> getByNextBoardId(Integer nextBoardId) {
        Optional<Board> byNextBoardId = boardRepository.findByNextBoardId(nextBoardId);
        if (byNextBoardId == null) {
            return null;
        }
        return byNextBoardId;
    }

    @Override
    public Optional<Board> getById(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return boardRepository.findById(id);
    }

    @Override
    public List<Board> getBoardByColumnsIds(List<Integer> columnsIds) {
        return boardRepository.findByColumnIdIn(columnsIds);
    }

    @Override
    public Board getBoardByCardId(Long cardId) {
        Optional<Board> board = boardRepository.findByCardId(cardId);
        if (board.isEmpty()) {
            throw BoardNotFoundException.EXCEPTION;
        }
        return board.get();
    }

    //    @Override
    //    public void batchUpdate(List<Board> boards) {
    //        boardRepository.saveAll(boards);
    //    }

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public void delete(Board board) {
        boardRepository.delete(board);
    }

    @Override
    public void saveAll(List<Board> board) {
        boardRepository.saveAll(board);
    }

    //    @Override
    //    public void update(Board board) {
    //        boardRepository.update(board);
    //    }

    /*    private void batchUpdate(List<Board> boards, String sql) {
                    jdbcTemplate.batchUpdate(
                                                    sql,
                                                    new BatchPreparedStatementSetter() {
                                                                    @Override
                                                                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                                                                                    //                    Board board = boards.get(i);
                                                                                    ps.setInt(1, boards.get(i).getLowLoc() + 1);
                                                                                    log.info(String.valueOf(boards.get(i).getLowLoc() + 1));
                                                                                    //                    ps.setString(2, new Date().toString());
                                                                    }

                                                                    @Override
                                                                    public int getBatchSize() {
                                                                                    return boards.size();
                                                                    }
                                                    });
    }*/

    //
    //        jdbcTemplate.batchUpdate(sql,
    //                boards,
    //                boards.size(),
    //                (PreparedStatement ps, Board board) -> {
    //                    @Override
    //                    public void setValues(PreparedStatement ps, int i) throws SQLException {
    //                        ps.setInt(1, board.getLowLoc() + 1);
    //                        ps.setString(2,new Date().toString());
    //                    }
    //                    @Override
    //                    public int getBatchSize() {
    //                        return subItems.size();
    //                    }
    //                    // 아래로 한칸씩만 위치 증가
    ////                    ps.setInt(1, board.getLowLoc() + 1);
    ////                    ps.setString(2,new Date().toString());
    //                });
    //    }
}
