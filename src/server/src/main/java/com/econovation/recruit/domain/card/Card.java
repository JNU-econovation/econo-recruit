package com.econovation.recruit.domain.card;

import com.econovation.recruit.domain.BaseTimeEntity;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Card extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Column(name = "work_card_info")
    private String workCardInfo;

    public Card(Applicant applicant, Board board) {
        this.applicant = applicant;
        this.workCardInfo = null;
        this.board = board;
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof Card)) {
//            return false;
//        }
//        return Objects.equals(id, this.id);
//    }
}
