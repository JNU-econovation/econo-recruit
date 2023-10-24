package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.applicant.domain.Answer;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class AnswerPageResponseDto {
    private Integer maxPage;
    private List<Answer> content;

    public static AnswerPageResponseDto of(Integer maxPage, List<Answer> content) {
        return AnswerPageResponseDto.builder().maxPage(maxPage).content(content).build();
    }
}
