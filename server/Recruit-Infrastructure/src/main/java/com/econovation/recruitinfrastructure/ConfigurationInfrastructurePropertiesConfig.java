package com.econovation.recruitinfrastructure;

import com.econovation.recruitinfrastructure.mail.GoogleMailProperties;
import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import com.econovation.recruitinfrastructure.slack.config.SlackTFProperties;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({SlackProperties.class, SlackTFProperties.class, GoogleMailProperties.class})
@Configuration
public class ConfigurationInfrastructurePropertiesConfig {}
