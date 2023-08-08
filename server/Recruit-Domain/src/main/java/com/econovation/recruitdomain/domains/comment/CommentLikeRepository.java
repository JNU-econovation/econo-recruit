package com.econovation.recruitdomain.domains.comment;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByComment(Comment comment);

    Boolean existsByIdpId(Integer idpId);
}
