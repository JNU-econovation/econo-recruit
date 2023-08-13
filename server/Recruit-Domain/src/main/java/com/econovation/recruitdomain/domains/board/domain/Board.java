package com.econovation.recruitdomain.domains.board.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import javax.persistence.*;
import javax.persistence.Column;
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

//    @Column(name = "prev_low_loc")
//    private Integer prevLowLoc;

    @Column(name = "next_low_loc")
    private Integer nextBoardID;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "column_id")
    private Integer columnId;

    public void updateLocation(Integer nextLowLoc) {
        this.nextBoardID = nextLowLoc;
    }

    public void updateNextBoardID(Integer nextBoardId) {
        this.nextBoardID = nextBoardId;
    }
}
