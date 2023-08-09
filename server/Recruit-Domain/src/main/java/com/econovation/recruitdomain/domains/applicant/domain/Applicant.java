package com.econovation.recruitdomain.domain.applicant;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "applicant")
public class Applicant extends BaseTimeEntity {
    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    // 희망분야
    @Column(name = "hope_field")
    private String hopeField;
    // 1지망
    @Column(name = "first_priority")
    private String firstPriority;

    // 2지망
    @Column(name = "second_priority")
    private String secondPriority;

    // 이름
    @Column(name = "name")
    private String name;

    // 핸드폰 번호
    @Column(name = "phone_number")
    private String phoneNumber;

    // 학번
    @Column(name = "student_id")
    private Integer studentId;

    // 학년
    @Column(name = "grade")
    private Integer grade;

    // 학기
    @Column(name = "semester")
    private Integer semester;

    // 전공
    @Column(name = "major")
    private String major;

    // 복수전공
    @Column(name = "double_major")
    private String doubleMajor;
    // 부전공
    @Column(name = "minor")
    private String minor;

    // 지원 경로
    @Column(name = "support_path")
    private String supportPath;

    // 이메일
    @Column(name = "email")
    private String email;
    /** insert 되기전 (persist 되기전) 실행된다. */
    @PrePersist
    public void prePersist() {}
}
