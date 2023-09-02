package com.econovation.recruitdomain.domains.dto;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicantPaginationResponseDto {
    List<Map<String, String>> applicants;
    Integer maxPage;
}
