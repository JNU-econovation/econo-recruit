package com.econovation.recruitdomain.domains.comment.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@SQLDelete(sql = "UPDATE comment SET is_deleted = true WHERE comment_id = ?")
@Where(clause = "is_deleted = false")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "applicant_id")
    private String applicantId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "content")
    private String content;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "idp_id")
    private Long idpId;

    public void delete() {
        this.isDeleted = true;
    }

    @PrePersist
    public void prePersist() {
        this.likeCount = this.likeCount == null ? 0 : this.likeCount;
        this.isDeleted = false;
    }

    public void setIdpId(Long idpId) {
        this.idpId = idpId;
    }

    public void plusLikeCount() {
        this.likeCount++;
    }

    public void minusLikeCount() {
        this.likeCount++;
    }

    public boolean isHost(Long idpId) {
        return this.idpId.equals(idpId);
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public Boolean isCardComment() {
        return this.cardId != null;
    }

    public Boolean isApplicantComment() {
        return this.applicantId != null;
    }
}
