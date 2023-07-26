package com.econovation.recruit.adapter.in.controller;


import com.econovation.recruit.application.port.in.BoardUseCase;
import com.econovation.recruit.application.port.in.CardRegisterUseCase;
import com.econovation.recruit.application.port.in.NavigationUseCase;
import com.econovation.recruitdomain.domain.board.Board;
import com.econovation.recruitdomain.domain.board.Navigation;
import com.econovation.recruitdomain.domain.card.Card;
import com.econovation.recruitdomain.domain.dto.CreateWorkCardDto;
import com.econovation.recruitdomain.domain.dto.UpdateLocationBoardDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class BoardRestController {
    private final BoardUseCase boardUseCase;
    private final CardRegisterUseCase cardRegisterUseCase;
    private final NavigationUseCase navigationUseCase;
    // 칸반보드 전체 조회 by navLoc
    @PostMapping("/boards/work-card")
    public ResponseEntity<Board> createWorkBoard(CreateWorkCardDto createWorkCardDto) {
        Map<String, Integer> newestLocationByNavLocAndColLoc =
                boardUseCase.getNewestLocationByNavLocAndColLoc(
                        createWorkCardDto.getNavLoc(), createWorkCardDto.getColLoc());
        Board board =
                boardUseCase.createWorkBoard(
                        createWorkCardDto.getWorkContent(),
                        createWorkCardDto.getNavLoc(),
                        newestLocationByNavLocAndColLoc.get("colLoc"),
                        newestLocationByNavLocAndColLoc.get("lowLoc"));
        return new ResponseEntity(board, HttpStatus.OK);
    }

    @PostMapping("/boards/location")
    public ResponseEntity<UpdateLocationBoardDto> updateLocationBoard(
            UpdateLocationBoardDto updateLocationBoardDto) {
        // gu
        //        Board boardByLocation =
        // boardLoadPort.getBoardByLocation(updateLocationBoardDto.getNavLoc(),
        // updateLocationBoardDto.getColLoc(), bov);
        // 기존 위치와 상충되면?
        if (boardUseCase.isDuplicateLocation(
                updateLocationBoardDto.getNavLoc(),
                updateLocationBoardDto.getColLoc(),
                updateLocationBoardDto.getLowLoc())) {
            // 현재 위치 와 도착 인덱스 사이를 추출
            // 추출된 데이터의 전체 lowLoc 를 재정렬 한다.
            boardUseCase.relocationBetweenStartToEndLowLoc(updateLocationBoardDto);
            //            boardUseCase.lagLowColBelowLocation(updateLocationBoardDto.getNavLoc(),
            // updateLocationBoardDto.getColLoc(), updateLocationBoardDto.getLowLoc());
            return new ResponseEntity(HttpStatus.OK);

        } else {
            Board board = boardUseCase.findById(updateLocationBoardDto.getId());
            Board updatedBoard =
                    boardUseCase.updateLocation(
                            board,
                            updateLocationBoardDto.getColLoc(),
                            updateLocationBoardDto.getLowLoc());
            return new ResponseEntity(updatedBoard, HttpStatus.OK);
        }
    }

    @GetMapping("/boards/cards")
    public List<Card> getCardAll() {
        return new ArrayList<>(cardRegisterUseCase.findAll());
    }

    @PostMapping("/boards/cards/delete")
    public ResponseEntity deleteCard(Integer cardId) {
        cardRegisterUseCase.deleteById(cardId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/boards/navigation")
    public ResponseEntity createNavigation(String navTitle) {
        Navigation navigation = navigationUseCase.createNavigation(navTitle);
        return new ResponseEntity(navigation, HttpStatus.OK);
    }

    @PostMapping("/boards/navigation/update")
    public ResponseEntity createNavigation(Integer navLoc, String navTitle) {
        Navigation navigation = navigationUseCase.updateNavigationByNavLoc(navLoc, navTitle);
        return new ResponseEntity(navigation, HttpStatus.OK);
    }

    @GetMapping("/boards/navigation")
    public Navigation getNavigationByNavLoc(Integer navLoc) {
        return boardUseCase.getNavigationByNavLoc(navLoc);
    }

    @GetMapping("/boards/navigations")
    public List<Navigation> getAllNavigation() {
        List<Navigation> navigations = boardUseCase.getAllNavigation();
        for (Navigation nav : navigations) {
            log.info(nav.getId().toString() + ":" + nav.getNavTitle() + ":" + nav.getNavTitle());
        }
        return navigations;
    }

    @PostMapping("/boards/navigation/delete")
    public ResponseEntity deleteNavigation(Integer navId) {
        navigationUseCase.deleteById(navId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
