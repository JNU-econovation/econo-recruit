package com.econovation.recruitdomain.domains.dto;

import java.util.List;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateScoreDto {
    private String applicantId;
    private List<Float> scoreVo;
}
