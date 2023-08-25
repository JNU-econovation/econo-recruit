package com.econovation.recruitinfrastructure;

import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import com.econovation.recruitinfrastructure.slack.config.SlackTFProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({SlackProperties.class, SlackTFProperties.class})
@Configuration
public class ConfigurationInfrastructurePropertiesConfig {}
