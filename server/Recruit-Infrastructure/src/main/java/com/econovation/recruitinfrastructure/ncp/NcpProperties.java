package com.econovation.recruitinfrastructure.ncp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "ncp")
public class NcpProperties {
    private String accessKey;
    private String secretKey;
    private String senderAddress;
    private String fromPhoneNumber;
    private String mailBaseUrl;
    private String mailApiUri;
    private String smsBaseUrl;
    private String smsApiUri;
}
