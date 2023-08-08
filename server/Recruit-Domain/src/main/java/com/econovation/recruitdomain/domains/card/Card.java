package com.econovation.recruitdomain.domains.card;


import com.econovation.recruitdomain.domains.BaseTimeEntity;
import com.econovation.recruitdomain.domains.applicant.Applicant;
import com.econovation.recruitdomain.domains.board.Board;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
