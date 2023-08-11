package com.econovation.recruitdomain.domains.card;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
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

    @Column(name = "board_id")
    private Integer boardId;

    @Column(name = "applicant_id")
    private Integer applicantId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "label_count")
    private Integer labelCount;

    @Column(name = "comment_count")
    private Integer commentCount;

    public void plusLabelCount() {
        this.labelCount++;
    }

    public void plusCommentCount() {
        this.commentCount++;
    }
}
