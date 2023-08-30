package com.econovation.recruitdomain.domains.dto;

import javax.annotation.Nullable;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateWorkCardDto {
    //    private Integer navigationId;
    private Integer columnId;
    private String title;
    private String content;
}
