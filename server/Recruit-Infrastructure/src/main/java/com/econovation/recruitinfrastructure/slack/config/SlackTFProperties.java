package com.econovation.recruitinfrastructure.slack.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "slack.tf")
public class SlackTFProperties {
    private String token;
    private String id;
    private String channel;
    private String username;
    private String iconUrl;
}
