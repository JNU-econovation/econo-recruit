package com.econovation.recruitdomain.domains.board.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    // hope_field 와 board.col_title
    @Query(
            value = "SELECT * " + "FROM Board as b " + "WHERE b.col_title = :hope_field",
            nativeQuery = true)
    List<Board> getByHopeField(@Param("hope_field") String hopeField);

    List<Board> findByNavLocAndColLoc();

    List<Board> findByNavLoc(Integer navigationId);

    List<Board> findByNavigationIdAndColumnsId(Integer navigationId, Integer columnsId);

    List<Board> findByNavigationId(Integer navigationId);

    List<Board> findByNavigationIdAndColumnId(Integer navigationId, Integer columnId);

    Optional<Board> findByNextBoardId(Integer nextBoardId);

    List<Board> findByColumnsIdIn(List<Integer> columnsIds);
}
