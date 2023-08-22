package com.econovation.recruitinfrastructure.idp.config;

import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(IdpErrorDecoder.class)
public class IdpConfig {

    @Bean
    @ConditionalOnMissingBean(value = ErrorDecoder.class)
    public IdpErrorDecoder commonFeignErrorDecoder() {
        return new IdpErrorDecoder();
    }

    @Bean
    Encoder formEncoder() {
        return new feign.form.FormEncoder();
    }
}
