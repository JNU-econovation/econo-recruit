package com.econovation.recruit.domain.applicant;

import com.econovation.recruit.domain.BaseTimeEntity;
import com.econovation.recruit.domain.card.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Getter
@DynamicUpdate
@Builder
public class Applicant extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//    @OneToOne
//    @JoinColumn(name = "card_id")
//    private Card card;

    @Column(name = "hope_field")
    private String hopeField;
    @Column(name = "first_priority")
    private String firstPriority;
    @Column(name = "second_priority")
    private String secondPriority;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "grade")
    private Integer grade;
    @Column(name = "semester")
    private Integer semester;
    @Column(name = "major")
    private String major;
    @Column(name = "double_major")
    private String doubleMajor;
    @Column(name = "minor")
    private String minor;

    @Column(name = "portfolio")
    private String portfolio;
    @Column(name = "support_path")
    private String supportPath;
    @Column(name = "plan")
    private String plan;
    @Column(name = "label_count")

    private Integer labelCount;
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     * Card 주입
    * */
//    public void presetCard(Card card){
//        this.card = card;
//    }

    /**
     * insert 되기전 (persist 되기전) 실행된다.
     * */
    @PrePersist
    public void prePersist() {
        this.labelCount = this.labelCount == null ? 0 : this.labelCount;
        this.commentCount = this.commentCount == null ? 0 : this.commentCount;
    }

    public void plusCommentCount() {
        this.commentCount = this.commentCount + 1;
    }
    public void minusCommentCount(){
        if(commentCount == 0) return;
        this.commentCount = this.commentCount - 1;
    }
    public void plusLabelCount(){
        this.labelCount = this.labelCount + 1;
    }
    public void minusLabelCount(){
        if(commentCount == 0) return;
        this.commentCount = this.commentCount - 1;
    }


}
