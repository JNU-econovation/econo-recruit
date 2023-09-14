package com.econovation.recruitinfrastructure.ncp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "ncp")
@ConstructorBinding
public class NcpProperties {
    private String accessKey;
    private String secretKey;
    private String senderAddress;
    private String sendUrl;
}
