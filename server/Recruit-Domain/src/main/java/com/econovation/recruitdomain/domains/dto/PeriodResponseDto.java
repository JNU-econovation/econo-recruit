package com.econovation.recruitdomain.domains.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodResponseDto {

    private LocalDateTime recruitStart;
    private LocalDateTime recruitEnd;
    private LocalDateTime firstPassDate;
    private LocalDateTime firstDiscussionEnd;
    private LocalDateTime finalDiscussionEnd;
    private LocalDateTime finalPassDate;

    public static PeriodResponseDto of(
            LocalDateTime recruitStart,
            LocalDateTime recruitEnd,
            LocalDateTime firstPassDate,
            LocalDateTime firstDiscussionEnd,
            LocalDateTime finalDiscussionEnd,
            LocalDateTime finalPassDate) {
        return new PeriodResponseDto(
                recruitStart, recruitEnd, firstPassDate, firstDiscussionEnd, finalDiscussionEnd, finalPassDate);
    }
}
