package com.econovation.recruitdomain.domains.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Data
@Getter
public class CreateWorkCardDto {
    private Integer columnId;
    private String title;
    @Nullable private String content;
}
