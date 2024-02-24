package com.econovation.recruitdomain.domains.comment.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCardId(Long cardId);

    List<Comment> findByApplicantId(String applicantId);

    @Modifying
    @Query("delete from Comment c where c.idpId = :idpId")
    void deleteByIdpId(@Param("idpId") Long idpId);
}
