package com.econovation.recruitinfrastructure.apache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "commons.email")
@ConstructorBinding
public class CommonsEmailProperties {
    private String host;
    private String senderAddress;
    private String senderName;
    private String password;
}
