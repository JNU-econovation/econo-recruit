package com.econovation.recruit.api.config.security;

import static com.econovation.recruitcommon.consts.RecruitStatic.PublicGetPatterns;
import static com.econovation.recruitcommon.consts.RecruitStatic.PublicPostPatterns;
import static com.econovation.recruitcommon.consts.RecruitStatic.RolePattern;
import static com.econovation.recruitcommon.consts.RecruitStatic.SwaggerPatterns;

import com.econovation.recruitcommon.helper.SpringEnvironmentHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final FilterConfig filterConfig;

    @Value("${swagger.user}")
    private String swaggerUser;

    @Value("${swagger.password}")
    private String swaggerPassword;

    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user =
                User.withUsername(swaggerUser)
                        .password(passwordEncoder().encode(swaggerPassword))
                        .roles("SWAGGER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(
                auth -> {
                    if (springEnvironmentHelper.isProdProfile()) {
                        auth.requestMatchers(SwaggerPatterns).denyAll();
                        auth.requestMatchers("/webjars/**").denyAll();
                    } else {
                        auth.requestMatchers(SwaggerPatterns).permitAll();
                        auth.requestMatchers("/webjars/**").permitAll();
                    }

                    auth.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                            .permitAll()
                            .requestMatchers(HttpMethod.OPTIONS, "/**")
                            .permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/graphql")
                            .permitAll()
                            .requestMatchers(HttpMethod.POST, PublicPostPatterns)
                            .permitAll()
                            .requestMatchers(HttpMethod.GET, PublicGetPatterns)
                            .permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/api/v1/interviewers/*")
                            .hasAnyRole("OPERATION", "PRESIDENT")
                            .requestMatchers(
                                    HttpMethod.PATCH, "/api/v1/applicants/{applicant-id}/status")
                            .hasAnyRole("OPERATION", "PRESIDENT")
                            .requestMatchers(HttpMethod.POST, "/api/v1/emails/*")
                            .hasAnyRole("OPERATION", "PRESIDENT")
                            .requestMatchers(HttpMethod.POST, "/api/v1/recruitment")
                            .hasAnyRole("OPERATION", "PRESIDENT")
                            .requestMatchers(
                                    HttpMethod.DELETE,
                                    "/api/v1/applicants/all/*",
                                    "/api/v1/applicants")
                            .hasAnyRole("OPERATION")
                            .requestMatchers(HttpMethod.GET, "/api/v1/applicants/names/**")
                            .hasAnyRole(RolePattern)
                            .requestMatchers(HttpMethod.POST, "/api/v1/comments/disclosure")
                            .hasAnyRole("OPERATION", "PRESIDENT")
                            .requestMatchers(HttpMethod.GET, "/api/v1/comments/disclosure")
                            .hasAnyRole("OPERATION", "PRESIDENT")
                            .requestMatchers(HttpMethod.PUT, "/api/v1/interviewers/*/roles")
                            .hasAnyRole("OPERATION")
                            .anyRequest()
                            .hasAnyRole(RolePattern);
                });

        http.with(filterConfig, c -> {});

        return http.build();
    }

    @Bean
    public static RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy(
                "ROLE_OPERATION > ROLE_PRESIDENT > ROLE_TF > ROLE_SWAGGER > ROLE_GUEST");
    }

    @Bean
    public static DefaultMethodSecurityExpressionHandler methodSecurityExpressionHandler(
            RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler handler =
                new DefaultMethodSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);
        return handler;
    }
}
