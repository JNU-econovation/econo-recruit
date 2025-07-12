package com.econovation.recruit.api.recruitment.dto;

import com.econovation.recruit.utils.vo.PageInfo;
import com.econovation.recruitdomain.domains.dto.RecruitmentResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecruitmentResponsesDto {

    private final PageInfo pageInfo;
    private final List<RecruitmentResponseDto> responses;

}
