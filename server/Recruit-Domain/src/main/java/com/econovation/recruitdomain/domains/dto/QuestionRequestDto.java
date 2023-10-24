package com.econovation.recruitdomain.domains.dto;

import javax.annotation.Nullable;
import lombok.Data;

@Data
public class QuestionRequestDto {
    private String type;
    private String name;
    @Nullable private Integer parentId;
}
