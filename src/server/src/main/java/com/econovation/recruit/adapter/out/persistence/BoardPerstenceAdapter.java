package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.BoardLoadPort;
import com.econovation.recruit.application.port.out.BoardRecordPort;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.board.BoardRepository;
import com.econovation.recruit.domain.board.Navigation;
import com.econovation.recruit.domain.dto.ResumeInsertDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

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
    public List<Board> getByHopeField(String hopeField) {
        return boardRepository.getByHopeField(hopeField);
    }

    @Override
    public Board getBoardByLocation(Integer navLoc, Integer colLoc, Integer lowLoc) {
        return boardRepository.findByNavLocAndColLocAndLowLoc(navLoc, colLoc, lowLoc);
    }

    @Override
    public Board getBoardById(Integer id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(NOT_MATCH_MESSAGE));
    }

    @Override
    public List<Board> getBoardByNavLocAndColLoc(Integer navLoc, Integer colLoc) {
        return boardRepository.findByNavLocAndColLoc(navLoc, colLoc);
    }

    @Override
    public List<Board> getByNavColAndColLoc(Integer navLoc, Integer colLoc) {
        return boardRepository.findByNavLocAndColLoc(navLoc, colLoc);
    }

    @Override
    public List<Board> getBoardByNavLoc(Integer navLoc) {
        return boardRepository.findByNavLoc(navLoc);
    }

    @Override
    public List<Board> getBoardBetweenLowLoc(Integer startLowLoc, Integer destinationLowLoc) {
        List<Board> all = boardRepository.findAll();
        if(startLowLoc < destinationLowLoc){
            return all.stream()
                    .filter(m -> m.getLowLoc() >= startLowLoc && m.getLowLoc() <= destinationLowLoc)
                    .toList();
        } else{
            return all.stream()
                    .filter(m -> m.getLowLoc() <= startLowLoc && m.getLowLoc() >= destinationLowLoc)
                    .toList();
        }
    }

    @Override
    public Board save(Board board) {
        Board save = boardRepository.save(board);
        if(save == null){
            throw new IllegalArgumentException(NO_BOARD_MESSAGE);
        }
        return save;
    }

    @Override
    @Transactional
    public List<Board> lagUpdateAll(List<Board> boards) {
        for (Board board: boards) {
            log.info(board.toString());
            //
            board.setLowLoc(board.getLowLoc()+1);
            boardRepository.save(board);
        }
        return boards;
//        if (!boards.isEmpty()) {
//            StopWatch timer = new StopWatch();
////            INSERT INTO food (`food_id`, `name`) VALUES (?, ?)
////            String sql = "UPDATE board SET low_loc=?, updated_at=:updatedAt";
//            String sql = "UPDATE 'board' SET low_loc=?";
//            timer.start();
//            batchUpdate(boards, sql);
//            timer.stop();
//            log.info("batchUpdate -> Total time in seconds: " + (timer.getStopTime() - timer.getStartTime()));
//            return boards;
//        }
////        }
//        throw new IllegalArgumentException(NOT_MATCH_MESSAGE);
    }

    @Override
    public void batchUpdate(List<Board> boards) {
        log.info("야야");
        boardRepository.saveAll(boards);
    }

//    @Override
//    public void update(Board board) {
//        boardRepository.update(board);
//    }

    private void batchUpdate(List<Board> boards, String sql) {
        jdbcTemplate.batchUpdate(sql,
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
    }


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
