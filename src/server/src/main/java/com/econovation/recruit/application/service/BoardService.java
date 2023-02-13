package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.BoardRegisterUseCase;
import com.econovation.recruit.application.port.out.BoardRecordPort;
import com.econovation.recruit.application.port.out.NavigationLoadPort;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.board.Navigation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService implements BoardRegisterUseCase {
    private final NavigationLoadPort navigationLoadPort;
    private final BoardRecordPort boardRecordPort;
    @Override
    public Board save(Map<String, Integer> newestLocation, String hopeField, Integer navLoc) {
        Navigation navigation = navigationLoadPort.getByNavLoc(navLoc);
        Board board = Board.builder()
                .colLoc(newestLocation.get("colLoc"))
                .colTitle(hopeField)
                .lowLoc(newestLocation.get("colLoc"))
                .navigation(navigation)
                .build();
        return boardRecordPort.save(board);
    }
}
