package com.econovation.recruit.api.email_template.util;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class DefaultEmailProperties {

    @Value("${econovation.recruit.open_chat_url}")
    private String openChatUrl;

    @Value("${econovation.recruit.open_chat_url_deadline}")
    private LocalDateTime openChatUrlDeadLine;

    @Value("${econovation.recruit.ot_schedule}")
    private LocalDateTime otSchedule;

    @Value("${econovation.recruit.ot_place}")
    private String otPlace;

    @Value("${econovation.file.path.portfolio}")
    private String filePath;

}
