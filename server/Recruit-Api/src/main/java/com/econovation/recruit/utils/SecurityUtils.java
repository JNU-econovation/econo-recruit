package com.econovation.recruit.utils;

import org.springframework.http.ResponseCookie;

public class SecurityUtils {
    public static ResponseCookie setCookie(String name, String value) {
        return ResponseCookie.from(name, value)
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .maxAge(2592000)
                .path("/")
                .build();
    }

    public static ResponseCookie logoutCookie(String name, String value) {
        return ResponseCookie.from(name, value)
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .maxAge(0)
                .path("/")
                .build();
    }
}
