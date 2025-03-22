package com.econovation.recruitinfrastructure.ncp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NcpSmsResponse {

    private String requestId;
    private String requestTime;
    private String statusCode;
    private String statusName;

}
