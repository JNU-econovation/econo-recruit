package com.econovation.recruitdomain.domains.card;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import java.util.UUID;
import javax.annotation.Nullable;
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

    @Column(name = "applicant_id")
    @Nullable
    private UUID applicantId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "label_count")
    private Integer labelCount;

    @Column(name = "comment_count")
    private Integer commentCount;

    @PrePersist
    public void prePersist() {
        this.labelCount = 0;
        this.commentCount = 0;
    }

    public void plusLabelCount() {
        this.labelCount++;
    }

    public void minusLabelCount() {
        this.labelCount--;
    }

    public void plusCommentCount() {
        this.commentCount++;
    }

    public void minusCommentCount() {
        this.commentCount--;
    }
}
