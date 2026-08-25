package com.econovation.recruitdomain.domains.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentSetUpDto {
    private Integer year;

    @NotNull
    @Schema(type = "string", example = "2026-02-14T23:38:00")
    private LocalDateTime startAt;

    @NotNull
    @Schema(type = "string", example = "2026-02-15T19:30:00")
    private LocalDateTime endAt;
}
