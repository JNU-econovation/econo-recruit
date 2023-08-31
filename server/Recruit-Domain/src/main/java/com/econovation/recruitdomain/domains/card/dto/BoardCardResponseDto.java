package com.econovation.recruitdomain.domains.card.dto;

import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.CardType;
import com.econovation.recruitdomain.domains.card.domain.Card;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@Builder
public class BoardCardResponseDto {
    private Long id;
    private Integer boardId;
    private Integer columnId;
    private Integer nextBoardId;
    private CardType cardType;
    private String title;
    private String content;
    private Integer labelCount;
    private Integer commentCount;
    private String firstPriority;
    private String secondPriority;
    private Boolean isLabeled;

    public static BoardCardResponseDto from(
            Card card,
            Board board,
            String firstPriority,
            String secondPriority,
            Boolean isLabeled) {
        return BoardCardResponseDto.builder()
                .boardId(board.getId())
                .nextBoardId(board.getNextBoardId())
                .id(card.getId())
                .cardType(board.getCardType())
                .columnId(board.getColumnId())
                .title(card.getTitle())
                .content(card.getContent())
                .labelCount(card.getLabelCount())
                .commentCount(card.getCommentCount())
                .firstPriority(firstPriority)
                .secondPriority(secondPriority)
                .isLabeled(isLabeled)
                .build();
    }
}
