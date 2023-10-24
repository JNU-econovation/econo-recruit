package com.econovation.recruitdomain.domains.board.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends JpaRepository<Columns, Integer> {
    List<Columns> findByNavigationId(Integer navigationId);

    Optional<Columns> findByNextColumnsIdAndNavigationId(Integer nextColLoc, Integer navigationId);

    Optional<Columns> findByNextColumnsId(Integer nextColumnsId);
}
