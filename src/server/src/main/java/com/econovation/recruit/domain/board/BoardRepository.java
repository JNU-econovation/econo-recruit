package com.econovation.recruit.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // hope_field 와 board.col_title
    @Query(value = "SELECT b " +
            "FROM Board as b " +
            "WHERE b.colTitle = :hope_field"
    )
    List<Board> getByHopeField(@Param("hope_field") String hopeField);

    @Query(value = "SELECT b " +
            "FROM Board as b " +
            "JOIN Navigation as n " +
            "ON n.navLoc = :navLoc " +
            "WHERE b.colLoc =:colLoc AND b.lowLoc =:lowLoc"
    )
    Optional<Board> findByNavLocAndColLocAndLowLoc(@Param("nav_loc") Integer navLoc, @Param("col_loc") Integer colLoc, @Param("low_loc")  Integer lowLoc);

    @Query(value = "SELECT b " +
            "FROM Board as b " +
            "JOIN Navigation as n " +
            "ON n.navLoc = :navLoc " +
            "WHERE b.colLoc =:colLoc"
    )
    List<Board> findByNavLocAndColLoc(Integer navLoc, Integer colLoc);

    @Query(value = "SELECT b " +
            "FROM Board as b " +
            "JOIN Navigation as n " +
            "ON n.navLoc = :navLoc "
    )
    List<Board> findByNavLoc(Integer navLoc);
}
