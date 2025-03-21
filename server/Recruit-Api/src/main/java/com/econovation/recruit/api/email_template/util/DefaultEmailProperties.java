package com.econovation.recruit.api.email_template.util;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class DefaultEmailProperties {

    private String openChatUrl;
    private LocalDateTime openChatUrlDeadLine;
    private LocalDateTime otSchedule;
    private String otPlace;
    private String filePath;

    public DefaultEmailProperties(
            @Value("${econovation.recruit.open_chat_url}") String openChatUrl,
            @Value("${econovation.recruit.open_chat_url_deadline}") String openChatUrlDeadLine,
            @Value("${econovation.recruit.ot_schedule}") String otSchedule,
            @Value("${econovation.recruit.ot_place}") String otPlace,
            @Value ("${econovation.file.path.portfolio}") String filePath
    ){
        this.openChatUrl = openChatUrl;
        this.openChatUrlDeadLine = LocalDateTime.parse(openChatUrlDeadLine);
        this.otSchedule = LocalDateTime.parse(otSchedule);
        this.otPlace = otPlace;
        this.filePath = filePath;
    }

}
