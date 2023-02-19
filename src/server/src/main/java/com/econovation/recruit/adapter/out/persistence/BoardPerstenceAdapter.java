package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.BoardLoadPort;
import com.econovation.recruit.application.port.out.BoardRecordPort;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.board.BoardRepository;
import com.econovation.recruit.domain.dto.ResumeInsertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.util.*;

@Component
@RequiredArgsConstructor
public class BoardPerstenceAdapter implements BoardLoadPort, BoardRecordPort {
    private static final String NO_NAVLOC_MESSAGE = "해당하는 메뉴가 존재하지 않습니다";
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
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<Board> lagUpdateAll(List<Board> boards) {
        String sql = "UPDATE board SET low_col = ?";
        if (!boards.isEmpty()) {
            batchUpdate(boards, sql);
            return boards;
        }
        throw new IllegalArgumentException(NOT_MATCH_MESSAGE);
    }

    private void batchUpdate(List<Board> boards, String sql) {
        jdbcTemplate.batchUpdate(sql,
                boards,
                boards.size(),
                (PreparedStatement ps, Board board) -> {
                    // 아래로 한칸씩만 위치 증가
                    ps.setInt(1, board.getLowLoc() + 1);
                });
    }
}
