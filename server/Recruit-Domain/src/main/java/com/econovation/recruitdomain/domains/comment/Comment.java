package com.econovation.recruitdomain.domains.comment;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import com.econovation.recruitdomain.domains.applicant.Applicant;
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
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Integer id;

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

    @Column(name = "idp_id")
    private Integer idpId;

    public void delete() {
        this.isDeleted = true;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    @PrePersist
    public void prePersist() {
        this.likeCount = this.likeCount == null ? 0 : this.likeCount;
        this.isDeleted = false;
    }

    public void plusLikeCount() {
        this.likeCount++;
    }

    public void minusLikeCount() {
        this.likeCount++;
    }
}
