package com.econovation.recruitinfrastructure;

import com.econovation.recruitinfrastructure.apache.CommonsEmailProperties;
import com.econovation.recruitinfrastructure.mail.GoogleMailProperties;
import com.econovation.recruitinfrastructure.ncp.NcpProperties;
import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import com.econovation.recruitinfrastructure.slack.config.SlackTFProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({
    SlackProperties.class,
    CommonsEmailProperties.class,
    SlackTFProperties.class,
    GoogleMailProperties.class,
    NcpProperties.class
})
@Configuration
public class ConfigurationInfrastructurePropertiesConfig {}
