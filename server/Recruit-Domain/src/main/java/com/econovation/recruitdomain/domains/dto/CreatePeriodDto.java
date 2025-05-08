package com.econovation.recruitdomain.domains.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePeriodDto {

    private LocalDateTime recruitStart;
    private LocalDateTime recruitEnd;
    private LocalDateTime firstPassDate;
    private LocalDateTime firstDiscussionEnd;
    private LocalDateTime finalDiscussionEnd;
    private LocalDateTime finalPassDate;
    
}
