package com.econovation.recruitdomain.domains.dto;

import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class UpdateRecordDto {
    @Nullable private String url;
    @Nullable private String record;
}
