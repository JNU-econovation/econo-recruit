package com.econovation.recruitinfrastructure.idp.config;

import com.econovation.recruitcommon.exception.OtherServerBadRequestException;
import com.econovation.recruitcommon.exception.OtherServerExpiredTokenException;
import com.econovation.recruitcommon.exception.OtherServerForbiddenException;
import com.econovation.recruitcommon.exception.OtherServerUnauthorizedException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class IdpErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400) {
            switch (response.status()) {
                case 401:
                    throw OtherServerUnauthorizedException.EXCEPTION;
                case 403:
                    throw OtherServerForbiddenException.EXCEPTION;
                case 419:
                    throw OtherServerExpiredTokenException.EXCEPTION;
                default:
                    throw OtherServerBadRequestException.EXCEPTION;
            }
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
