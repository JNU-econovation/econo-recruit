package com.econovation.recruit.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

@Configuration
@RequiredArgsConstructor
@Profile({"prod", "staging", "dev"})
public class ServletFilterConfig implements WebMvcConfigurer {

    private final HttpContentCacheFilter httpContentCacheFilter;
    private final ForwardedHeaderFilter forwardedHeaderFilter;

    @Bean
    public FilterRegistrationBean setResourceUrlEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ResourceUrlEncodingFilter());
        registrationBean.setOrder(Integer.MAX_VALUE - 2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean setForwardedHeaderFilterOrder() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(forwardedHeaderFilter);
        registrationBean.setOrder(Integer.MAX_VALUE - 1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean setHttpContentCacheFilterOrder() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(httpContentCacheFilter);
        registrationBean.setOrder(Integer.MAX_VALUE);
        return registrationBean;
    }
}
