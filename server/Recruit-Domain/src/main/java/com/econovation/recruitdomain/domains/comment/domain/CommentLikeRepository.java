package com.econovation.recruitdomain.domains.comment.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    List<CommentLike> findByCommentId(Long commentId);

    Boolean existsByIdpId(Long idpId);

    Optional<CommentLike> findByCommentIdAndIdpId(Long commentId, Long idpId);

    List<CommentLike> findByCommentIdIn(List<Long> commentIds);

    @Modifying
    @Query("delete from CommentLike cl where cl.idpId = :idpId")
    void deleteByInterviewerId(@Param("idpId") Long idpId);
}
