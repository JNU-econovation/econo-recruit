package com.econovation.recruit.api.applicant.dto;

import com.econovation.recruit.utils.vo.PageInfo;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswersResponseDto {
    private PageInfo pageInfo;
    private List<Map<String, Object>> answers;

    public static AnswersResponseDto of(List<Map<String, Object>> list, PageInfo pageInfo) {
        return new AnswersResponseDto(pageInfo, list);
    }
}
