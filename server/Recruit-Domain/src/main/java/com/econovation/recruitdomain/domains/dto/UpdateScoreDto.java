package com.econovation.recruitdomain.domains.dto;

import java.util.UUID;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UpdateScoreDto {
    private UUID applicantId;
    private String criteria;
    private Float score;
}
