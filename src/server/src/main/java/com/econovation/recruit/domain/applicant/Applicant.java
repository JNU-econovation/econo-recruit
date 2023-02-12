package com.econovation.recruit.domain.applicant;

import com.econovation.recruit.domain.board.BaseTimeEntity;
import com.econovation.recruit.domain.card.Card;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Applicant extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    //지원분야
    private String hopeField;
    private String firstPriority;
    private String secondPriority;
    private String name;
    private String phoneNumber;
    private String email;
    private Integer studentId;
    private Integer grade;
    private String semester;
    private String major;
    private String doubleMajor;
    private String minor;
    private String likeCount;
    private String commentCount;






}
