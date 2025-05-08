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
    private LocalDateTime passDate;
    private LocalDateTime firstDiscussionEnd;
    private LocalDateTime finalDiscussionEnd;

    public static PeriodResponseDto of(
            LocalDateTime recruitStart,
            LocalDateTime recruitEnd,
            LocalDateTime passDate,
            LocalDateTime firstDiscussionEnd,
            LocalDateTime finalDiscussionEnd) {
        return new PeriodResponseDto(
                recruitStart, recruitEnd, passDate, firstDiscussionEnd, finalDiscussionEnd);
    }
}
