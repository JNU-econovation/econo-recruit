package com.econovation.recruitdomain.domains.dto;

import javax.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UpdateLocationColumnDto {
    @Min(1)
    private Integer columnId;

    private Integer targetColumnId;
}
