package com.econovation.recruitdomain.domains.period.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Period extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recruit_start")
    private LocalDateTime recruitStart;

    @Column(name = "recruit_end")
    private LocalDateTime recruitEnd;

    @Column(name = "pass_date")
    private LocalDateTime firstPassDate;

    @Column(name = "first_discussion_end")
    private LocalDateTime firstDiscussionEnd;

    @Column(name = "final_discussion_end")
    private LocalDateTime finalDiscussionEnd;

    @Column(name = "final_pass_date")
    private LocalDateTime finalPassDate;

    public Period(
            LocalDateTime recruitStart,
            LocalDateTime recruitEnd,
            LocalDateTime firstPassDate,
            LocalDateTime firstDiscussionEnd,
            LocalDateTime finalDiscussionEnd,
            LocalDateTime finalPassDate) {
        this.recruitStart = recruitStart;
        this.recruitEnd = recruitEnd;
        this.firstPassDate = firstPassDate;
        this.firstDiscussionEnd = firstDiscussionEnd;
        this.finalDiscussionEnd = finalDiscussionEnd;
        this.finalPassDate = finalPassDate;
    }

    public static Period of(
            LocalDateTime recruitStart,
            LocalDateTime recruitEnd,
            LocalDateTime firstPassDate,
            LocalDateTime firstDiscussionEnd,
            LocalDateTime finalDiscussionEnd,
            LocalDateTime finalPassDate) {
        return new Period(
                recruitStart,
                recruitEnd,
                firstPassDate,
                firstDiscussionEnd,
                finalDiscussionEnd,
                finalPassDate);
    }
}
