package com.econovation.recruitdomain.domains.recruitment.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecruitmentStart {

    private final Long recruitmentId;

}
