package com.econovation.recruitdomain.domains.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UpdateLocationColumnDto {
    private Integer columnId;
    private Integer targetColumnId;
}
