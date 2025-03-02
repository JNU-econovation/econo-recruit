package com.econovation.recruitdomain.domains.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class CreateColumnsDto {
    private String title;
    private Integer year;
}
