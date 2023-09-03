package com.econovation.recruitinfrastructure.ncp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {
    private String requestId;
    private int count;
}