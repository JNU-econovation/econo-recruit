package com.econovation.recruit.domain.card;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.board.BaseTimeEntity;
import com.econovation.recruit.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Card extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Column(name = "work_card_info")
    private String workCardInfo;

    public Card(Applicant applicant, Board board) {
        this.applicant = applicant;
        this.workCardInfo = null;
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        return Objects.equals(id, this.id);
    }
}
