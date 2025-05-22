package com.econovation.recruitdomain.domains.dto;

import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailReservationDto {

    private Set<String> applicantIds;
    private long reservedAtTimestamp;

    @AssertTrue(message = "현재보다 이전 시간으로는 예약할 수 없습니다.")
    public boolean validateReservedAt(){
        return reservedAtTimestamp > System.currentTimeMillis();
    }
}
