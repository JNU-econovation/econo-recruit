package com.econovation.recruitdomain.domains.card.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @Column(name = "applicant_id")
    @Nullable
    private String applicantId;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "label_count")
    private Integer labelCount;

    @Column(name = "comment_count")
    private Integer commentCount;

    public static Card empty() {
        return Card.builder().
                id(0L).
                applicantId("").
                title("").
                content("").
                labelCount(0).
                commentCount(0).
                build();
    }

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

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateTitle(String title) {
        this.title = title;
    }
}
