package com.econovation.recruitdomain.domains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
