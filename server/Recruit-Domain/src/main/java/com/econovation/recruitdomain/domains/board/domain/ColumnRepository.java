package com.econovation.recruitdomain.domains.board.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends JpaRepository<Columns, Integer> {
    List<Columns> findByNavigationId(Integer navigationId);

    @Query("SELECT c FROM Columns c WHERE c.navigationId = :navigationId AND c.year = :year")
    List<Columns> findByNavigationIdAndYear(Integer navigationId, Integer year);

    Optional<Columns> findByNextColumnsIdAndNavigationId(Integer nextColLoc, Integer navigationId);

    Optional<Columns> findByNextColumnsId(Integer nextColumnsId);

    @Query(
            "select case when count(c) > 0 then true else false end from Columns c where c.title = :title and c.year = :year ")
    boolean existsByTitleAndYear(String title, Integer year);
}
