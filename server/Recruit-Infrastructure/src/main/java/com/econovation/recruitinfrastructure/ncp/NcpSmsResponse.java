package com.econovation.recruitinfrastructure.ncp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NcpSmsResponse {

    private String requestId;
    private String requestTime;
    private String statusCode;
    private String statusName;
}
