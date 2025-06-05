package com.econovation.recruitdomain.domains.whitelist.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "access_token")
public class AccessToken {
    @Id
    private Long id;
    @Indexed
    private String token;

    @TimeToLive
    private Long expiration;
}
