package com.econovation.recruitinfrastructure.slack.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "slack.webhook")
public class SlackProperties {
    private String url;
    private String channel;
    private String username;
    private String iconUrl;
}
