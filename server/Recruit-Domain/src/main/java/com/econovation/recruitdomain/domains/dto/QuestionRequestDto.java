package com.econovation.recruitdomain.domains.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class QuestionRequestDto {
    private String type;
    private String name;
    @Nullable private Integer parentId;
}
