package com.econovation.recruit.api.card.controller;

import static com.econovation.recruitcommon.consts.RecruitStatic.BOARD_SUCCESS_DELETE_MESSAGE;
import static com.econovation.recruitcommon.consts.RecruitStatic.BOARD_SUCCESS_REGISTER_MESSAGE;

import com.econovation.recruit.api.card.docs.CreateBoardExceptionDocs;
import com.econovation.recruit.api.card.docs.CreateColumnsExceptionDocs;
import com.econovation.recruit.api.card.docs.UpdateBoardExceptionDocs;
import com.econovation.recruit.api.card.usecase.BoardLoadUseCase;
import com.econovation.recruit.api.card.usecase.BoardRegisterUseCase;
import com.econovation.recruit.api.card.usecase.CardRegisterUseCase;
import com.econovation.recruit.application.port.in.NavigationUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitdomain.domains.board.domain.Navigation;
import com.econovation.recruitdomain.domains.card.Card;
import com.econovation.recruitdomain.domains.dto.CreateWorkCardDto;
import com.econovation.recruitdomain.domains.dto.UpdateLocationBoardDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "[2.0]. 칸반보드 API", description = "칸반보드 관련 API")
@Slf4j
public class BoardRestController {
    private final BoardLoadUseCase boardLoadUseCase;
    private final BoardRegisterUseCase boardRecordUseCase;
    private final CardRegisterUseCase cardRegisterUseCase;
    private final NavigationUseCase navigationUseCase;
    // 칸반보드 전체 조회 by navLoc
    @Operation(summary = "업무 칸반보드 생성", description = "업무 칸반(지원자가 아닌) 생성")
    @ApiErrorExceptionsExample(CreateBoardExceptionDocs.class)
    @PostMapping("/boards/work-card")
    public ResponseEntity<String> createWorkBoard(CreateWorkCardDto createWorkCardDto) {
        cardRegisterUseCase.saveWorkCard(createWorkCardDto);
        return new ResponseEntity(BOARD_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    @Operation(summary = "지원서 칸반보드 열(세로줄) 생성", description = "지원서 칸반보드 열(세로줄) 생성")
    @ApiErrorExceptionsExample(CreateColumnsExceptionDocs.class)
    @PostMapping("/boards/navigation/{navigation-id}/columns")
    public ResponseEntity<String> createBoardColumn(
            @PathVariable("navigation-id") Integer navigationId, String title) {
        boardRecordUseCase.createColumn(title, navigationId);
        return new ResponseEntity(BOARD_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    @Operation(summary = "지원서 칸반보드 위치 수정")
    @ApiErrorExceptionsExample(UpdateBoardExceptionDocs.class)
    @PostMapping("/boards/location")
    public ResponseEntity<String> updateLocationBoard(
            UpdateLocationBoardDto updateLocationBoardDto) {
        boardRecordUseCase.relocateCard(updateLocationBoardDto);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @GetMapping("/boards/cards")
//    public List<Card> getCardAll() {
//        return new ArrayList<>(cardLoadUseCase.findAll());
//    }

    @PostMapping("/boards/cards/delete")
    public ResponseEntity<String> deleteCard(Integer cardId) {
        cardRegisterUseCase.deleteById(cardId);
        return new ResponseEntity<>(BOARD_SUCCESS_DELETE_MESSAGE, HttpStatus.OK);
    }

    @PostMapping("/boards/navigation")
    public ResponseEntity<Navigation> createNavigation(String navTitle) {
        Navigation navigation = navigationUseCase.createNavigation(navTitle);
        return new ResponseEntity<>(navigation, HttpStatus.OK);
    }

    @PostMapping("/boards/navigation/update")
    public ResponseEntity<Navigation> createNavigation(Integer navLoc, String navTitle) {
        Navigation navigation = navigationUseCase.updateNavigationByNavLoc(navLoc, navTitle);
        return new ResponseEntity<>(navigation, HttpStatus.OK);
    }

    @GetMapping("/boards/navigation")
    public Navigation getNavigationByNavLoc(Integer navLoc) {
        return boardLoadUseCase.getNavigationByNavLoc(navLoc);
    }

    @GetMapping("/boards/navigations")
    public List<Navigation> getAllNavigation() {
        List<Navigation> navigations = boardLoadUseCase.getAllNavigation();
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