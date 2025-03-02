package com.econovation.recruit.api.card.controller;

import static com.econovation.recruitcommon.consts.RecruitStatic.COLUMN_SUCCESS_REGISTER_MESSAGE;

import com.econovation.recruit.api.card.docs.CreateColumnsExceptionDocs;
import com.econovation.recruit.api.card.usecase.BoardLoadUseCase;
import com.econovation.recruit.api.card.usecase.BoardRegisterUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitdomain.domains.board.dto.ColumnsResponseDto;
import com.econovation.recruitdomain.domains.dto.CreateColumnsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "access-token")
@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
@Tag(name = "[2.0]. 칸반보드 API V2", description = "칸반보드 관련 API")
public class BoardRestControllerV2 {
    private final BoardLoadUseCase boardLoadUseCase;
    private final BoardRegisterUseCase boardRecordUseCase;

    @Operation(
            summary = "지원서 세로줄 조회(by NavigationId and Year)",
            description = "navigationId와 year(기수)에 해당하는 모든 세로줄을 조회합니다., 세로줄이 없으면 빈 배열을 반환합니다.")
    @GetMapping("/boards/navigations/{navigation-id}/columns")
    public ResponseEntity<List<ColumnsResponseDto>> getBoardColumnByNavigationId(
            @PathVariable("navigation-id") Integer navigationId,
            @RequestParam("year") Integer year) {
        return new ResponseEntity<>(
                boardLoadUseCase.getColumnsByNavigationIdAndYear(navigationId, year),
                HttpStatus.OK);
    }

    @Operation(
            summary = "지원서 칸반보드 열(세로줄) 생성",
            description = "지원서 칸반보드 열(세로줄) 생성, title과 year를 Body로 받아옵니다.")
    @ApiErrorExceptionsExample(CreateColumnsExceptionDocs.class)
    @PostMapping("/boards/navigations/{navigation-id}/columns")
    public ResponseEntity<String> createBoardColumn(
            @PathVariable("navigation-id") Integer navigationId,
            @RequestBody CreateColumnsDto createColumnsDto) {
        boardRecordUseCase.createColumnWithYear(navigationId, createColumnsDto);
        return new ResponseEntity<>(COLUMN_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }
}
