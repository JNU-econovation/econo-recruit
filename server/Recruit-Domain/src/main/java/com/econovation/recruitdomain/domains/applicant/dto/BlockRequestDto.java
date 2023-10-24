package com.econovation.recruitdomain.domains.applicant.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@RequiredArgsConstructor
public class BlockRequestDto {
    private String name;
    // 한글 기준으로 1000자 이하
    @Length(max = 1000)
    private String answer;
}
