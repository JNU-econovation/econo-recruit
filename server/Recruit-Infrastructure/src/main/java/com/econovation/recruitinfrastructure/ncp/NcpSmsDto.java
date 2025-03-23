package com.econovation.recruitinfrastructure.ncp;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
public record NcpSmsDto(
        String type, String from, String subject, String content, List<Message> messages) {

    @Getter
    public static class Message {
        private final String to;

        public Message(String to) {
            this.to = to;
        }
    }
}
