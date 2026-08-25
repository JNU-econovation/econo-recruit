package com.econovation.recruit.api.config.security;

import static com.econovation.recruitcommon.consts.RecruitStatic.AUTH_HEADER;
import static com.econovation.recruitcommon.consts.RecruitStatic.BEARER;
import static com.econovation.recruitcommon.consts.RecruitStatic.PublicGetPatterns;
import static com.econovation.recruitcommon.consts.RecruitStatic.PublicPostPatterns;
import static com.econovation.recruitcommon.consts.RecruitStatic.StaticResourcePatterns;
import static com.econovation.recruitcommon.consts.RecruitStatic.SwaggerPatterns;

import com.econovation.recruitcommon.dto.AccessTokenInfo;
import com.econovation.recruitcommon.exception.InvalidTokenException;
import com.econovation.recruitcommon.jwt.JwtTokenProvider;
import com.econovation.recruitdomain.out.WhitelistLoadPort;
import java.io.IOException;
import java.util.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final WhitelistLoadPort whitelistLoadPort;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = resolveToken(request);

        if (token == null || !whitelistLoadPort.existsByToken(token)) {
            throw InvalidTokenException.EXCEPTION;
        }

        Authentication authentication = getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return "OPTIONS".equals(method)
                || matchesAny(antPathMatcher, path, SwaggerPatterns)
                || matchesAny(antPathMatcher, path, StaticResourcePatterns)
                || ("POST".equals(method) && matchesAny(antPathMatcher, path, PublicPostPatterns))
                || ("GET".equals(method) && matchesAny(antPathMatcher, path, PublicGetPatterns));
    }

    private boolean matchesAny(AntPathMatcher antPathMatcher, String path, String[] patterns) {
        return Arrays.stream(patterns).anyMatch(pattern -> antPathMatcher.match(pattern, path));
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
