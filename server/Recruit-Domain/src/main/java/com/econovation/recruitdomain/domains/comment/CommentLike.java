package com.econovation.recruitdomain.domains.comment;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_like_id")
    private Comment id;

    @Column(name = "comment_id")
    private Comment commentId;

    @Column(name = "idp_id")
    private Comment idpId;
}
