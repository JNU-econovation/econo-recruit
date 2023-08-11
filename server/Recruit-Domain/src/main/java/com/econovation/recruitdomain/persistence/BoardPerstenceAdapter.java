package com.econovation.recruitdomain.persistence;

import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.BoardRepository;
import com.econovation.recruitdomain.out.BoardLoadPort;
import com.econovation.recruitdomain.out.BoardRecordPort;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BoardPerstenceAdapter implements BoardLoadPort, BoardRecordPort {
    private static final String NO_NAVLOC_MESSAGE = "해당하는 메뉴가 존재하지 않습니다";
    private static final String NO_BOARD_MESSAGE = "해당하는 BOARD가 존재하지 않습니다";
    private static final String NOT_MATCH_MESSAGE = "일치하는 Board를 찾을 수 없습니다";
    private final BoardRepository boardRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Board getBoardByLocation(Integer navLoc, Integer colLoc, Integer lowLoc) {
        return boardRepository.findByNavLocAndColLocAndLowLoc(navLoc, colLoc, lowLoc);
    }

    @Override
    public Board getBoardById(Integer id) {
        return boardRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_MATCH_MESSAGE));
    }

    @Override
    public List<Board> getBoardByNavLavigationIdAndColLoc(Integer navigationId, Integer colLoc) {
        //        input colLoc 이 prevColLoc 보다 크고 nextColLoc 보다 작은 board 들을 가져온다
        Integer prevColLoc = colLoc - 1;
        Integer nextColLoc = colLoc + 1;
        return boardRepository.findByNavLocAndPrevColLocAndNextColLoc(
                navigationId, prevColLoc, nextColLoc);
    }

    @Override
    public List<Board> getBoardByNavLoc(Integer navigationId) {
        return boardRepository.findByNavigationId(navigationId);
    }

    /*    @Override
    public List<Board> getBoardBetweenLowLoc(Integer startLowLoc, Integer destinationLowLoc) {
    				List<Board> all = boardRepository.findAll();
    				if (startLowLoc < destinationLowLoc) {
    								return all.stream()
    																.filter(m -> m.getLowLoc() >= startLowLoc && m.getLowLoc() <= destinationLowLoc)
    																.collect(Collectors.toList());
    				} else {
    								return all.stream()
    																.filter(m -> m.getLowLoc() <= startLowLoc && m.getLowLoc() >= destinationLowLoc)
    																.collect(Collectors.toList());
    				}
    }
    */

    //    @Override
    public void batchUpdate(List<Board> boards) {
        boardRepository.saveAll(boards);
    }

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public void delete(Board board) {
        boardRepository.delete(board);
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
