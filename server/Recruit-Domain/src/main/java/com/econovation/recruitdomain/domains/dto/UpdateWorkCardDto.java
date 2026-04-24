package com.econovation.recruitdomain.domains.dto;

import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class UpdateWorkCardDto {
    @Nullable private String title;
    @Nullable private String content;
}
