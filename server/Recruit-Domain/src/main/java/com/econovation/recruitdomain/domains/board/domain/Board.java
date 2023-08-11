package com.econovation.recruitdomain.domains.board.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Integer id;

    @Column(name = "navigation_id")
    private Integer navigationId;

    @Column(name = "prev_col_loc")
    private Integer prevColLoc;

    @Column(name = "next_col_loc")
    private Integer nextColLoc;

    @Column(name = "prev_low_loc")
    private Integer prevLowLoc;

    @Column(name = "next_low_loc")
    private Integer nextLowLoc;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "card_id")
    private Integer cardId;
}
