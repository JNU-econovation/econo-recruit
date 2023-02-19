package com.econovation.recruit.domain.dto;


import com.econovation.recruit.domain.board.Navigation;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Data
public class BoardResponseDto {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "navigation_id")
    private Navigation navigation;

    @Column(name = "col_loc")
    private Integer colLoc;

    @Column(name = "col_title")
    private String colTitle;

    @Column(name = "low_loc")
    private Integer lowLoc;
}
