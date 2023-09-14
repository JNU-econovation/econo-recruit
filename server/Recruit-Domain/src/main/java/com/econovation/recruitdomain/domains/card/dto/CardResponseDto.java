package com.econovation.recruitdomain.domains.card.dto;

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
    private String title;
    private String content;

    public static CardResponseDto from(Card card) {
        return new CardResponseDto(card.getTitle(), card.getContent());
    }
}
