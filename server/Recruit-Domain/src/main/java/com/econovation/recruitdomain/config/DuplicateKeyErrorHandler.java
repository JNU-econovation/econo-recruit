package com.econovation.recruitdomain.config;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DuplicateKeyErrorHandler implements ListenerInvocationErrorHandler {

    @Override
    public void onError(
            Exception exception, EventMessage<?> event, EventMessageHandler eventHandler)
            throws Exception {
        if (isDuplicateKeyException(exception)) {
            log.warn("중복 데이터 무시 - eventId: {}", event.getIdentifier());
            return;
        }
        throw exception;
    }

    private boolean isDuplicateKeyException(Exception e) {
        return e instanceof DuplicateKeyException;
    }
}
