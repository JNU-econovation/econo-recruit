package com.econovation.recruit.domain.comment;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.board.BaseTimeEntity;
import com.econovation.recruit.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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


}
