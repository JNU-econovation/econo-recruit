package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.BoardUseCase;
import com.econovation.recruit.domain.board.Board;
import com.econovation.recruit.domain.board.Navigation;
import com.econovation.recruit.domain.card.Card;
import com.econovation.recruit.domain.card.CardRepository;
import com.econovation.recruit.domain.dto.CreateWorkCardDto;
import com.econovation.recruit.domain.dto.UpdateLocationBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class BoardRestController {
    private final BoardUseCase boardUseCase;
    private final CardRepository cardRepository;
    // 칸반보드 전체 조회 by navLoc
    @PostMapping("/board/work-card")
    public ResponseEntity<Board> createWorkBoard(CreateWorkCardDto createWorkCardDto) {
        Map<String, Integer> newestLocationByNavLocAndColLoc = boardUseCase.getNewestLocationByNavLocAndColLoc(createWorkCardDto.getNavLoc(), createWorkCardDto.getColLoc());
        Board board = boardUseCase.createWorkBoard(createWorkCardDto.getWorkContent(),createWorkCardDto.getNavLoc(),
                newestLocationByNavLocAndColLoc.get("colLoc"), newestLocationByNavLocAndColLoc.get("lowLoc"));
        return new ResponseEntity(board, HttpStatus.OK);
    }

    @PostMapping("/board/location")
    public ResponseEntity<UpdateLocationBoardDto> updateLocationBoard(UpdateLocationBoardDto updateLocationBoardDto){
        // 기존 위치와 상충되면?
        if (boardUseCase.isDuplicateLocation(updateLocationBoardDto.getNavLoc(), updateLocationBoardDto.getColLoc(), updateLocationBoardDto.getLowLoc())) {
            // 아래 인덱스를 다 한칸씩 밀어! -> 빈 자리에 추가해
            boardUseCase.lagLowColBelowLocation(updateLocationBoardDto.getNavLoc(), updateLocationBoardDto.getColLoc(), updateLocationBoardDto.getLowLoc());
        }
        Board board = boardUseCase.findById(updateLocationBoardDto.getId());
        Board updatedBoard = boardUseCase.updateLocation(board,updateLocationBoardDto.getColLoc(), updateLocationBoardDto.getLowLoc());
        return new ResponseEntity(updatedBoard, HttpStatus.OK);
    }
    @GetMapping("/boards/cards")
    public List<Card> getCardAll(){
        return cardRepository.findAll();
    }

    @GetMapping("/boards/navigation")
    public Navigation getNavigationByNavLoc(Integer navLoc) {
        return boardUseCase.getNavigationByNavLoc(navLoc);
    }

    @GetMapping("/boards/navigations")
    public List<Navigation> getAllNavigation() {
        List<Navigation> navigations = boardUseCase.getAllNavigation();
        for (Navigation nav: navigations) {
            log.info(nav.getId().toString() + ":" + nav.getNavTitle() +  ":" +  nav.getNavTitle());
        }
        return navigations;
    }

}
