package com.econovation.recruit.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/swagger-ui.html", "/swagger-ui/index.html");
        registry.addRedirectViewController("/swagger-ui/", "/swagger-ui/index.html");
        registry.addRedirectViewController("/swagger-ui/index.html/", "/swagger-ui/index.html");
    }
}
