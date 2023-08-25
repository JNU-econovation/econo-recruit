package com.econovation.recruit.api.config.security;


import com.econovation.recruitcommon.dto.AccessTokenInfo;
import com.econovation.recruitcommon.jwt.JwtTokenProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import static com.econovation.recruitcommon.consts.RecruitStatic.AUTH_HEADER;
import static com.econovation.recruitcommon.consts.RecruitStatic.BEARER;

@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = resolveToken(request);

        if (token != null) {
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        // 쿠키방식 지원
        Cookie accessTokenCookie = WebUtils.getCookie(request, "accessToken");
        if (accessTokenCookie != null) {
            return accessTokenCookie.getValue();
        }
        // 기존 jwt 방식 지원
        String rawHeader = request.getHeader(AUTH_HEADER);

        if (rawHeader != null
                && rawHeader.length() > BEARER.length()
                && rawHeader.startsWith(BEARER)) {
            return rawHeader.substring(BEARER.length());
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        AccessTokenInfo accessTokenInfo = jwtTokenProvider.parseAccessToken(token);

        UserDetails userDetails =
                new AuthDetails(accessTokenInfo.getUserId().toString(), accessTokenInfo.getRole());
        return new UsernamePasswordAuthenticationToken(
                userDetails, "user", userDetails.getAuthorities());
    }
}
