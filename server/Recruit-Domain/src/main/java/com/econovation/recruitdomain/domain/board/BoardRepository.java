package com.econovation.recruitdomain.domain.board;


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

    @Query(
            value =
                    "SELECT * "
                            + "FROM Board as b "
                            + "JOIN Navigation as n "
                            + "ON n.nav_loc = :nav_loc "
                            + "WHERE b.col_loc =:col_loc",
            nativeQuery = true)
    List<Board> findByNavLocAndColLoc(
            @Param("nav_loc") Integer navLoc, @Param("col_loc") Integer colLoc);

    @Query(
            value =
                    "SELECT * "
                            + "FROM Board as b "
                            + "JOIN Navigation as n "
                            + "ON n.navLoc = :nav_loc ",
            nativeQuery = true)
    List<Board> findByNavLoc(@Param("nav_loc") Integer navLoc);

    //    @Modifying(clearAutomatically = true)
    //    @Query("UPDATE Board as b SET b.lowLoc=bd.")
    //    void update(@Param(value = "bd") Board board);
}
