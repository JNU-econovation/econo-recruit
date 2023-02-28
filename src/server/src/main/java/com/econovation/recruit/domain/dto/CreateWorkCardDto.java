package com.econovation.recruit.domain.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateWorkCardDto {
    private String workContent;
    private Integer navLoc;
    private Integer colLoc;
}
