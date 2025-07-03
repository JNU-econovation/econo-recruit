package com.econovation.recruitinfrastructure.ncp;

import com.econovation.recruitcommon.exception.OtherServerBadRequestException;
import com.econovation.recruitcommon.exception.OtherServerForbiddenException;
import com.econovation.recruitcommon.exception.OtherServerInternalSeverErrorException;
import com.econovation.recruitcommon.exception.OtherServerNotFoundException;
import com.econovation.recruitcommon.exception.OtherServerUnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NcpInfoErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(response.body().asInputStream()));
        } catch (IOException e) {
            throw OtherServerBadRequestException.EXCEPTION;
        }

        switch (response.status()) {
            case 401:
                log.error("NCP 인증에 실패하였습니다. {}", readBody(br));
                throw OtherServerUnauthorizedException.EXCEPTION;
            case 403:
                log.error("NCP 인증에 실패하였습니다. {}", readBody(br));
                throw OtherServerForbiddenException.EXCEPTION;
            case 404:
                log.error("NCP 인증에 실패하였습니다. {}", readBody(br));
                throw OtherServerNotFoundException.EXCEPTION;
            case 500:
                log.error("NCP 인증에 실패하였습니다. {}", readBody(br));
                throw OtherServerInternalSeverErrorException.EXCEPTION;
            default:
                log.error("NCP 인증에 실패하였습니다. {}", readBody(br));
                throw OtherServerBadRequestException.EXCEPTION;
        }
    }

    private String readBody(BufferedReader reader) {
        try {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw OtherServerBadRequestException.EXCEPTION;
        }
    }
}
