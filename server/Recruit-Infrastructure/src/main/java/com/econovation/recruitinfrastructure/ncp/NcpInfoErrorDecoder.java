package com.econovation.recruitinfrastructure.ncp;

import com.econovation.recruitcommon.exception.OtherServerBadRequestException;
import com.econovation.recruitcommon.exception.OtherServerForbiddenException;
import com.econovation.recruitcommon.exception.OtherServerInternalSeverErrorException;
import com.econovation.recruitcommon.exception.OtherServerNotFoundException;
import com.econovation.recruitcommon.exception.OtherServerUnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class NcpInfoErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 401:
                throw OtherServerUnauthorizedException.EXCEPTION;
            case 403:
                throw OtherServerForbiddenException.EXCEPTION;
            case 404:
                throw OtherServerNotFoundException.EXCEPTION;
            case 500:
                throw OtherServerInternalSeverErrorException.EXCEPTION;
            default:
                throw OtherServerBadRequestException.EXCEPTION;
        }
    }
}
