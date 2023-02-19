package com.econovation.recruit.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    // hope_field 와 board.col_title
    @Query(value = "SELECT b " +
            "FROM Board as b " +
            "WHERE b.colTitle = :hope_field"
            ,nativeQuery = true
    )
    List<Board> getByHopeField(@Param("hope_field") String hopeField);

    @Query(value = "SELECT b " +
            "FROM Board as b " +
            "JOIN Navigation as n " +
            "ON n.navLoc = :navLoc " +
            "WHERE b.colLoc =:colLoc AND b.lowLoc =:lowLoc"
            ,nativeQuery = true
    )
    Board findByNavLocAndColLocAndLowLoc(@Param("navLoc") Integer navLoc, @Param("colLoc") Integer colLoc, @Param("lowLoc")  Integer lowLoc);

    @Query(value = "SELECT b " +
            "FROM Board as b " +
            "JOIN Navigation as n " +
            "ON n.navLoc = :navLoc " +
            "WHERE b.colLoc =:colLoc"
            ,nativeQuery = true
    )
    List<Board> findByNavLocAndColLoc(Integer navLoc, Integer colLoc);

    @Query(value = "SELECT b " +
            "FROM Board as b " +
            "JOIN Navigation as n " +
            "ON n.navLoc = :navLoc "
            ,nativeQuery = true
    )
    List<Board> findByNavLoc(Integer navLoc);
}
