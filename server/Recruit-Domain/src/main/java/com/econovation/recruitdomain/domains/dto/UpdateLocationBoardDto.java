package com.econovation.recruitdomain.domains.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UpdateLocationBoardDto {
    private Integer boardId;
    private Integer targetBoardId;
}
