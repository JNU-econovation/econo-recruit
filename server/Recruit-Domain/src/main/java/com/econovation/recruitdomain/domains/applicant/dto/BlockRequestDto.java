package com.econovation.recruitdomain.domains.applicant.dto;

import com.econovation.recruitcommon.annotation.XssProtected;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BlockRequestDto {
    //    private String type;
    private String name;
    private String answer;
}
