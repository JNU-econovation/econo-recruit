package com.econovation.recruit.domain.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UpdateLocationBoardDto {
    private Integer id;
    private Integer navLoc;
    private Integer colLoc;
    private String workCardInfo;
    private Integer lowLoc;
}
