package com.econovation.recruitdomain.domains.comment.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id")
    private Long id;

    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "idp_id")
    private Long idpId;
}
