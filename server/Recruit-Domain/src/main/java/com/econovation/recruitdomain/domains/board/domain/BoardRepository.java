package com.econovation.recruitdomain.domains.board.domain;

import java.util.List;
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

    @Query(
            value =
                    "SELECT * "
                            + "FROM Board as b "
                            + "JOIN Navigation as n "
                            + "ON n.nav_loc = :nav_loc "
                            + "WHERE b.col_loc =:col_loc AND b.low_loc =:low_loc",
            nativeQuery = true)
    Board findByNavLocAndColLocAndLowLoc(
            @Param("nav_loc") Integer navLoc,
            @Param("col_loc") Integer colLoc,
            @Param("low_loc") Integer lowLoc);

    List<Board> findByNavLocAndColLoc();

    List<Board> findByNavLoc(Integer navigationId);

    List<Board> findByNavLocAndPrevColLocAndNextColLoc(
            Integer navLoc, Integer prevColLoc, Integer nextColLoc);

    List<Board> findByNavigationId(Integer navigationId);
}
