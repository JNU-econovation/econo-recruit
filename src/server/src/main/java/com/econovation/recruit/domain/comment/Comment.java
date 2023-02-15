package com.econovation.recruit.domain.comment;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
    @Column(name = "parent_id")
    private Long parentId;
    @Column(name = "content")
    private String content;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "like_count")
    private Integer likeCount;

    public void delete() {
        this.isDeleted = true;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    @PrePersist
    public void prePersist() {
        this.likeCount = this.likeCount == null ? 0 : this.likeCount;
    }

    public void plusLikeCount(){
        this.likeCount++;
    }

    public void minusLikeCount(){
        this.likeCount++;
    }
}
