package com.econovation.recruitdomain.domains.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailReservationDto {

    private Set<String> applicantIds;
    private long reservedAtTimestamp;
}
