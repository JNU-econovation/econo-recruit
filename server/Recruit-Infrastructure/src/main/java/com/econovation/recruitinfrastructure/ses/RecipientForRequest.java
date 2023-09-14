package com.econovation.recruitinfrastructure.ses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipientForRequest {
    private String address;
    private String name;
    private String type = "R";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Parameters parameters; // 매개변수 모델 추가
}
