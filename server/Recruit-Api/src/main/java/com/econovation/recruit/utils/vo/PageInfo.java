package com.econovation.recruit.utils.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PageInfo {
    @Schema(description = "현재 페이지")
    private int currentPage; // 현재 페이지

    @Schema(description = "총 게시글의 수")
    private int listCount; // 총 게시글의 수 [ SELECT COUNT(*) FROM BOARD ]

    @Schema(description = "한 페이지에 보여질 페이징 수")
    private int pageLimit; // 한 페이지에 보여질 페이징 수 ex) 총 페이지가 13개면, 1 ~ 10 / 11 ~ 13

    @Schema(description = "시작 페이지")
    private int startPage; // 시작 페이지  (1    11    21    31    41)

    @Schema(description = "끝 페이지")
    private int endPage; // 끝 페이지    (10    20    30    40    50)

    @Schema(description = "한 페이지에 보여질 게시글의 수")
    private int boardLimit; // 한 페이지에 보여질 게시글의 수

    public PageInfo(long totalCount, int page) {
        this.currentPage = page;
        this.listCount = (int) totalCount;
        this.pageLimit = 10;
        this.startPage = ((page - 1) / pageLimit) * pageLimit + 1;
        this.endPage = startPage + pageLimit - 1;
        this.boardLimit = 10;
        if (endPage > (listCount / boardLimit) + 1) {
            endPage = (listCount / boardLimit) + 1;
        }
    }
}
