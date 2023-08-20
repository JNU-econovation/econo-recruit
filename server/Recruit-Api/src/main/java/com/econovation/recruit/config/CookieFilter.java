package com.econovation.recruit.config;

import com.econovation.recruit.api.interviewer.usecase.InterviewerUseCase;
import com.econovation.recruitdomain.domains.interviewer.domain.Role;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.Base64;
import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
@RequiredArgsConstructor
public class CookieFilter extends OncePerRequestFilter {
    @Value("${auth.jwt.secret}")
    private String secretKey;

    private final InterviewerUseCase interviewerUseCase;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Long getIdpId(String token) {
        return Long.valueOf(
                Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject());
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = resolveToken(request.getHeader("Authorization"));
        if (request.getRequestURI().startsWith("/swagger")
                || request.getRequestURI().startsWith("/api-docs")
                || request.getRequestURI().startsWith("/api/v1/interviewers")) {
            log.info("permisrsion access");
            filterChain.doFilter(request, response);
            return;
        }
        if (token == null) {
            log.info("token is null");
            return;
        }
        Long idpId = getIdpId(token);
        filterChain.doFilter(request, response);
        if (request.getRequestURI().startsWith("/api/v1/interviewers")) {
            String role = interviewerUseCase.getById(idpId).getRole().name();
            if (role.equals(Role.ROLE_PRESIDENT.name())
                    ||
                    //                    role.equals(Role.ROLE_TF.name()) ||
                    role.equals(Role.ROLE_OPERATION.name())) {
                log.info("ADMIN ACCESS");
                filterChain.doFilter(request, response);
            }
            return;
        }
        // 전체 url 체크
        /*if(request.getRequestURI().startsWith("/api/v1/*")){
                        String role = interviewerUseCase.getById(Math.toIntExact(idpId)).getRole().name();
                        if(role.equals(Role.ROLE_PRESIDENT.name()) ||
                                                        role.equals(Role.ROLE_TF.name()) ||
                                                        role.equals(Role.ROLE_OPERATION.name())){
                                        log.info("ADMIN ACCESS");
                                        filterChain.doFilter(request,response);
                        }
                        return;
        }*/
        // token은 있는데 다른 요청일 경우 넘어가자
        log.info("token은 있는데 다른 요청일 경우 넘어가자");
        filterChain.doFilter(request, response);
    }

    private String resolveToken(String authorization) {
        return authorization != null ? authorization.substring(7) : null;
    }
}
