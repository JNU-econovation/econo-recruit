package com.econovation.recruitinfrastructure.ses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipientForRequest {
    private String address;
    private String name;
    private String type = "R";
    private Object parameters;
}
