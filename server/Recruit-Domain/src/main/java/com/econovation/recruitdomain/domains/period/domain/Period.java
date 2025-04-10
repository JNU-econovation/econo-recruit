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
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Period extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recruit_start")
    private LocalDateTime recruitStart;

    @Column(name = "recruit_end")
    private LocalDateTime recruitEnd;

    @Column(name = "pass_date")
    private LocalDateTime passDate;

    @Column(name = "first_discussion_end")
    private LocalDateTime firstDiscussionEnd;

    @Column(name = "final_discussion_end")
    private LocalDateTime finalDiscussionEnd;

    public Period(LocalDateTime recruitStart, LocalDateTime recruitEnd, LocalDateTime passDate,
                  LocalDateTime firstDiscussionEnd, LocalDateTime finalDiscussionEnd) {
        this.recruitStart = recruitStart;
        this.recruitEnd = recruitEnd;
        this.passDate = passDate;
        this.firstDiscussionEnd = firstDiscussionEnd;
        this.finalDiscussionEnd = finalDiscussionEnd;
    }

    public static Period of(LocalDateTime recruitStart, LocalDateTime recruitEnd, LocalDateTime passDate, LocalDateTime firstDiscussionEnd, LocalDateTime finalDiscussionEnd) {
        return new Period(recruitStart, recruitEnd, passDate, firstDiscussionEnd, finalDiscussionEnd);
    }

}
