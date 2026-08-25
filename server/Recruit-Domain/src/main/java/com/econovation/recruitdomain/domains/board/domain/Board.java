package com.econovation.recruitdomain.domains.board.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.lang.Nullable;

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

    @Column(name = "next_board_id")
    private Integer nextBoardId;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "column_id")
    private Integer columnId;

    @Column(name = "navigation_id")
    private Integer navigationId;

    public void updateNextBoardID(@Nullable Integer nextBoardId) {
        this.nextBoardId = nextBoardId;
    }

    public void updateColumnId(@Nullable Integer columnId) {
        this.columnId = columnId;
    }

    /**
     * columnId와 navigationId를 받아서 Invisible Board 반환
     *
     * @param columnId
     * @param navigationId
     * @return
     */
    public static Board creatInvisibleBoard(int columnId, int navigationId) {
        return Board.builder()
                .cardId(null)
                .nextBoardId(null)
                .columnId(columnId)
                .navigationId(navigationId)
                .cardType(CardType.INVISIBLE)
                .build();
    }
}
