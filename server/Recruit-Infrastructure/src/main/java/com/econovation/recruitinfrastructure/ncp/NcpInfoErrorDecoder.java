package com.econovation.recruitinfrastructure.ncp;

import com.econovation.recruitcommon.exception.OtherServerBadRequestException;
import com.econovation.recruitcommon.exception.OtherServerForbiddenException;
import com.econovation.recruitcommon.exception.OtherServerInternalSeverErrorException;
import com.econovation.recruitcommon.exception.OtherServerNotFoundException;
import com.econovation.recruitcommon.exception.OtherServerUnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NcpInfoErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 401:
                log.error("NCP 인증에 실패하였습니다. {}", response.body());
                throw OtherServerUnauthorizedException.EXCEPTION;
            case 403:
                log.error("NCP 인증에 실패하였습니다. {}", response.body());
                throw OtherServerForbiddenException.EXCEPTION;
            case 404:
                log.error("NCP 인증에 실패하였습니다. {}", response.body());
                throw OtherServerNotFoundException.EXCEPTION;
            case 500:
                log.error("NCP 인증에 실패하였습니다. {}", response.body());
                throw OtherServerInternalSeverErrorException.EXCEPTION;
            default:
                log.error("NCP 인증에 실패하였습니다. {}", response.body());
                throw OtherServerBadRequestException.EXCEPTION;
        }
    }
}
