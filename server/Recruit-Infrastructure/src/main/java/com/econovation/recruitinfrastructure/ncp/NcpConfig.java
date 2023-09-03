package com.econovation.recruitinfrastructure.ncp;

import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(NcpInfoErrorDecoder.class)
public class NcpConfig {
    @Bean
    @ConditionalOnMissingBean(value = ErrorDecoder.class)
    public NcpInfoErrorDecoder ncpInfoErrorDecoder() {
        return new NcpInfoErrorDecoder();
    }
}
