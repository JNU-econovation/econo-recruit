package com.econovation.recruitdomain.domains.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateWorkCardDto {
    //    private Integer navigationId;
    private Integer colLoc;
    private String title;
    private String content;
}
