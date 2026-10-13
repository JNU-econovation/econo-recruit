package com.econovation.recruit.api.config.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verifyNoInteractions;

import com.econovation.recruitcommon.exception.GlobalErrorCode;
import com.econovation.recruitcommon.jwt.JwtTokenProvider;
import com.econovation.recruitdomain.out.WhitelistLoadPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class JwtTokenFilterTest {

    private final JwtTokenProvider jwtTokenProvider =
            org.mockito.Mockito.mock(JwtTokenProvider.class);
    private final WhitelistLoadPort whitelistLoadPort =
            org.mockito.Mockito.mock(WhitelistLoadPort.class);

    @Test
    void returnsForbiddenWithoutAccessToken() throws Exception {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider, whitelistLoadPort);
        JwtExceptionFilter jwtExceptionFilter =
                new JwtExceptionFilter(new ObjectMapper().findAndRegisterModules());
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/v1/applicants");
        MockHttpServletResponse response = new MockHttpServletResponse();

        jwtExceptionFilter.doFilterInternal(
                request,
                response,
                (servletRequest, servletResponse) ->
                        jwtTokenFilter.doFilterInternal(
                                request, response, (ignoredRequest, ignoredResponse) -> {}));

        assertEquals(GlobalErrorCode.ACCESS_TOKEN_NOT_EXIST.getStatus(), response.getStatus());
        assertTrue(response.getContentType().startsWith("application/json"));
        assertTrue(response.getContentAsString().contains("AUTH_403_2"));
        verifyNoInteractions(whitelistLoadPort);
    }
}
