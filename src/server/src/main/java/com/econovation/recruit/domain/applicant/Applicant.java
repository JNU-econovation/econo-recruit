package com.econovation.recruit.domain.applicant;

import com.econovation.recruit.domain.BaseTimeEntity;
import com.econovation.recruit.domain.card.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Builder
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
    private Integer likeCount;
    private Integer commentCount;

    /**
     * Card 주입
    * */
    public void presetCard(Card card){
        this.card = card;
    }

    /**
     * insert 되기전 (persist 되기전) 실행된다.
     * */
    @PrePersist
    public void prePersist() {
        this.likeCount = this.likeCount == null ? 0 : this.likeCount;
        this.commentCount = this.commentCount == null ? 0 : this.commentCount;
    }

    public Long getId() {
        return id;
    }

    public String getHopeField() {
        return hopeField;
    }
    public Integer getLikeCount() {
        return likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }
    public void minusCommentCount(){
        if(commentCount == 0) return;
        this.commentCount = this.commentCount - 1;
    }
}
