package com.econovation.recruit.api.config.security;

import com.econovation.recruitcommon.jwt.JwtTokenProvider;
import com.econovation.recruitdomain.out.WhitelistLoadPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FilterConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    //    private final AccessDeniedFilter accessDeniedFilter;
    private final JwtExceptionFilter jwtExceptionFilter;
    private final JwtTokenProvider jwtTokenProvider;
    private final WhitelistLoadPort whitelistLoadPort;

    @Override
    public void configure(HttpSecurity builder) {
        builder.addFilterBefore(
                new JwtTokenFilter(jwtTokenProvider, whitelistLoadPort),
                BasicAuthenticationFilter.class);
        builder.addFilterBefore(jwtExceptionFilter, JwtTokenFilter.class);
        //        builder.addFilterBefore(accessDeniedFilter, FilterSecurityInterceptor.class);
    }
}
