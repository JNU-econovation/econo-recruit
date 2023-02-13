package com.econovation.recruit.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // hope_field 와 board.col_title
    @Query(value = "SELECT b " +
            "FROM Board as b " +
            "WHERE b.colTitle = :hope_field"
    )
    List<Board> getByHopeField(@Param("hope_field") String hopeField);
}
