package com.econovation.recruitdomain.domains.comment.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByCommentId(Long commentId);

    Boolean existsByIdpId(Long idpId);

    Optional<CommentLike> findByCommentIdAndIdpId(Long commentId, Long idpId);
}
