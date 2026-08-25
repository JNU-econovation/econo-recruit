package com.econovation.recruitcommon.config;

import com.econovation.recruitcommon.jwt.JwtProperties;
import com.econovation.recruitcommon.properties.IdpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({IdpProperties.class, JwtProperties.class})
@Configuration
public class ConfigurationCommonPropertiesConfig {}
