package com.econovation.recruitcommon.config;

import com.econovation.recruitcommon.jwt.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({JwtProperties.class})
@Configuration
public class ConfigurationCommonPropertiesConfig {}
