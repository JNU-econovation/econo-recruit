package com.econovation.recruitinfrastructure.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.mail")
public class GoogleMailProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
}
