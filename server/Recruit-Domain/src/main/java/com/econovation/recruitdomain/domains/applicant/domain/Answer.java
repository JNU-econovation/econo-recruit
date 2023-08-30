package com.econovation.recruitdomain.domains.applicant.domain;

import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.common.events.AnswerRegisteredEvent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "answer")
public class Answer {
    @Id
    @Column(name = "answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "applicant_id", nullable = false)
    private String applicantId;

    @PostPersist
    public void postPersist() {
        AnswerRegisteredEvent answerRegisteredEvent =
                AnswerRegisteredEvent.builder().applicantId(applicantId.toString()).build();
        Events.raise(answerRegisteredEvent);
    }
}
