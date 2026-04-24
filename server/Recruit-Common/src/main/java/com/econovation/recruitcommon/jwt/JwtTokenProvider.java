package com.econovation.recruitcommon.jwt;

import static com.econovation.recruitcommon.consts.RecruitStatic.*;

import com.econovation.recruitcommon.dto.AccessTokenInfo;
import com.econovation.recruitcommon.dto.TokenResponse;
import com.econovation.recruitcommon.exception.ExpiredTokenException;
import com.econovation.recruitcommon.exception.InvalidTokenException;
import com.econovation.recruitcommon.exception.RefreshTokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    public TokenResponse createToken(Long idpId, String role) {
        String accessToken = generateAccessToken(idpId, role);
        String refreshToken = generateRefreshToken(idpId);
        return new TokenResponse(accessToken, refreshToken);
    }

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    public boolean isAccessToken(String token) {
        return getJws(token).getPayload().get(TOKEN_TYPE).equals(ACCESS_TOKEN);
    }

    public boolean isRefreshToken(String token) {
        return getJws(token).getPayload().get(TOKEN_TYPE).equals(REFRESH_TOKEN);
    }

    public AccessTokenInfo parseAccessToken(String token) {
        if (isAccessToken(token)) {
            Claims claims = getJws(token).getPayload();
            return AccessTokenInfo.builder()
                    .userId(Long.parseLong(claims.getSubject()))
                    .role((String) claims.get(TOKEN_ROLE))
                    .build();
        }
        throw InvalidTokenException.EXCEPTION;
    }

    public Long parseRefreshToken(String token) {
        try {
            if (isRefreshToken(token)) {
                Claims claims = getJws(token).getBody();
                return Long.parseLong(claims.getSubject());
            }
        } catch (ExpiredTokenException e) {
            throw RefreshTokenExpiredException.EXCEPTION;
        }
        throw InvalidTokenException.EXCEPTION;
    }

    public String generateAccessToken(Long id, String role) {
        final Date issuedAt = new Date();
        final Date accessTokenExpiresIn =
                new Date(issuedAt.getTime() + jwtProperties.getAccessExp() * MILLI_TO_SECOND);

        return buildAccessToken(id, issuedAt, accessTokenExpiresIn, role);
    }

    public String generateRefreshToken(Long id) {
        final Date issuedAt = new Date();
        final Date refreshTokenExpiresIn =
                new Date(issuedAt.getTime() + jwtProperties.getRefreshExp() * MILLI_TO_SECOND);
        return buildRefreshToken(id, issuedAt, refreshTokenExpiresIn);
    }

    private String buildAccessToken(
            Long id, Date issuedAt, Date accessTokenExpiresIn, String role) {
        final SecretKey encodedKey = getSecretKey();
        return Jwts.builder()
                .issuer(TOKEN_ISSUER)
                .issuedAt(issuedAt)
                .subject(id.toString())
                .claim(TOKEN_TYPE, ACCESS_TOKEN)
                .claim(TOKEN_ROLE, role)
                .expiration(accessTokenExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    private String buildRefreshToken(Long id, Date issuedAt, Date accessTokenExpiresIn) {
        final SecretKey encodedKey = getSecretKey();
        return Jwts.builder()
                .issuer(TOKEN_ISSUER)
                .issuedAt(issuedAt)
                .subject(id.toString())
                .claim(TOKEN_TYPE, REFRESH_TOKEN)
                .expiration(accessTokenExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    public Long getRefreshTokenTTlSecond() {
        return jwtProperties.getRefreshExp();
    }

    public Long getAccessTokenTTlSecond() {
        return jwtProperties.getAccessExp();
    }
}
