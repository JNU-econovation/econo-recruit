package com.econovation.recruitdomain.domains.applicant.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplicationStartDto {

    private LocalDateTime startAt;

}
