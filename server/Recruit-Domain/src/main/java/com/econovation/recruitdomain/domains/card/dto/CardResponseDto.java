package com.econovation.recruitdomain.domains.card.dto;

import com.econovation.recruitdomain.domains.board.domain.Board;
import com.econovation.recruitdomain.domains.board.domain.CardType;
import com.econovation.recruitdomain.domains.card.domain.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CardResponseDto {
    private Integer boardId;
    private Integer nextBoardId;
    private Integer cardId;
    private CardType cardType;
    private String title;
    private String content;
    private Integer labelCount;
    private Integer commentCount;

    public static CardResponseDto from(Card card, Board board) {
        return new CardResponseDto(
                board.getId(),
                board.getNextBoardId(),
                card.getId(),
                board.getCardType(),
                card.getTitle(),
                card.getContent(),
                card.getLabelCount(),
                card.getCommentCount());
    }
}
