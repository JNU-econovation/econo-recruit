package com.econovation.recruitdomain.domains.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateRecordDto {
    private UUID applicantId;
    private String url;
    private String record;
}
