package com.econovation.recruitdomain.domains.dto;

import javax.annotation.Nullable;
import lombok.Getter;

@Getter
public class UpdateRecordDto {
    @Nullable private String url;
    @Nullable private String record;
}
