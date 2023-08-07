// package com.econovation.recruitdomain.domain.applicant;
//
//
// import com.econovation.recruitdomain.domain.BaseTimeEntity;
// import javax.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// @Entity
// @NoArgsConstructor
// @AllArgsConstructor
// @Getter
// @Builder
// @Table(name = "applicant")
// public class Applicant extends BaseTimeEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//    @Column(name = "hope_field")
//    private String hopeField;
//
//    @Column(name = "first_priority")
//    private String firstPriority;
//
//    @Column(name = "second_priority")
//    private String secondPriority;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "student_id")
//    private Integer studentId;
//
//    @Column(name = "grade")
//    private Integer grade;
//
//    @Column(name = "semester")
//    private Integer semester;
//
//    @Column(name = "major")
//    private String major;
//
//    @Column(name = "double_major")
//    private String doubleMajor;
//
//    @Column(name = "minor")
//    private String minor;
//
//    @Column(name = "portfolio")
//    private String portfolio;
//
//    @Column(name = "support_path")
//    private String supportPath;
//
//    @Column(name = "plan")
//    private String plan;
//
//    @Column(name = "label_count")
//    private Integer labelCount;
//
//    @Column(name = "comment_count")
//    private Integer commentCount;
//
//    /** Card 주입 */
//    //    public void presetCard(Card card){
//    //        this.card = card;
//    //    }
//
//    /** insert 되기전 (persist 되기전) 실행된다. */
//    @PrePersist
//    public void prePersist() {
//        this.labelCount = this.labelCount == null ? 0 : this.labelCount;
//        this.commentCount = this.commentCount == null ? 0 : this.commentCount;
//    }
//
//    public void plusCommentCount() {
//        this.commentCount = this.commentCount + 1;
//    }
//
//    public void minusCommentCount() {
//        if (commentCount == 0) return;
//        this.commentCount = this.commentCount - 1;
//    }
//
//    public void plusLabelCount() {
//        this.labelCount = this.labelCount + 1;
//    }
//
//    public void minusLabelCount() {
//        if (commentCount == 0) return;
//        this.commentCount = this.commentCount - 1;
//    }
// }
