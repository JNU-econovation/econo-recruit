package com.econovation.recruit.api.applicant.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswersByYearQuery {
    private Integer year;
    private Integer page;
}
