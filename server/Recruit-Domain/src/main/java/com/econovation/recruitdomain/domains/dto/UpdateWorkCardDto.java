package com.econovation.recruitdomain.domains.dto;

import javax.annotation.Nullable;
import lombok.Getter;

@Getter
public class UpdateWorkCardDto {
    @Nullable private String title;
    @Nullable private String content;
}
