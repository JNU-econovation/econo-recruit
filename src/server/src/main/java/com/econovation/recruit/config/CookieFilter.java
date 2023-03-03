package com.econovation.recruit.config;

import com.econovation.recruit.application.port.in.InterviewerUseCase;
import com.econovation.recruit.domain.interviewer.Role;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CookieFilter extends OncePerRequestFilter {
    @Value("${spring.jwt.secret-key}")
    private String secretKey;

    @Autowired
    private InterviewerUseCase interviewerUseCase;

    public Long getIdpId(String token) {
        return Long.valueOf(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request.getHeader("Authorization"));
        Long idpId = getIdpId(token);
        String role = interviewerUseCase.getById(Math.toIntExact(idpId)).getRole().value();
        if (token == null ) {
            return;
        }
        if(request.getRequestURI().startsWith("/swagger") || request.getRequestURI().startsWith("/api-docs")){
            filterChain.doFilter(request,response);
        }
        if(request.getRequestURI().startsWith("/api/v1/interviewers")){
            if(role.equals(Role.ROLE_ADMIN.value())){
                filterChain.doFilter(request,response);
            }
            else{
                return;
            }
        }
        // token은 있는데 다른 요청일 경우 넘어가자
        filterChain.doFilter(request,response);
    }

    private String resolveToken(String authorization) {
        return authorization != null ? authorization.substring(7) : null;
    }
}
