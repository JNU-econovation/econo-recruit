package com.econovation.recruitinfrastructure.apache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "commons.email")
public class CommonsEmailProperties {
    private String host;
    private String senderAddress;
    private String senderName;
    private String password;
}
