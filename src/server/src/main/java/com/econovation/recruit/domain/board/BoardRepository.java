package com.econovation.recruit.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "SELECT b " +
            "FROM Board as b " +
            "WHERE b.colTitle = :hope_field"
//            "ON b.col_title = :hope_field"
//            , nativeQuery = true)
    )
    // hope_field 와 board.col_title
    List<Board> getByHopeField(@Param("hope_field") String hopeField);
}
